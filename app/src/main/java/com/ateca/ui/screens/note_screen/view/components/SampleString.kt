package com.ateca.ui.screens.note_screen.view.components

import org.intellij.lang.annotations.Language


@Language("Markdown")
const val MIXED_MY_CUSTOM_IMAGE = """
[[!!!!!!!!Seriousdfgdfgdfg]](file:///android_asset/serios.jpg)
"""

@Language("Markdown")
const val NOTE_CUSTOM = """
[[The_car|http://hellsoft.se]]
"""

@Language("Markdown")
const val MIXED_MY_CUSTOM_LINC = """
[!!!!!!!!Seriousdfgdfgdfg](http://hellsoft.se)
"""

@Language("Markdown")
const val input = "---" +
        "\nhello: world" +
        "\n..." +
        "\n" +
        "\ngreat"

@Language("Markdown")
const val MIXED_MD = """
### Markdown Header

This is regular text without formatting in a single paragraph.

![!!!!!!!!Seriousdfgdfgdfg](file:///android_asset/serios.jpg)

Images can also be inline: ![Serious](file:///android_asset/serios.jpg). [Links](http://hellsoft.se) and `inline code` also work. This *is* text __with__ inline styles for *__bold and italic__*. Those can be nested.

Here is a code block:
```javascript
function codeBlock() {
    return true;
}
```

+ Bullet
+ __Lists__
+ Are
+ *Cool*

1. **First**
1. *Second*
1. Third
1. [Fourth is clickable](https://google.com)  
   1. And
   1. Sublists
1. Mixed
   - With
   - Bullet
   - Lists

100) Lists
100) Can
100) Have
100) *Custom*
100) __Start__
100) Numbers

- List
- Of
- Items
  - With
  - Sublist

> A blockquote is useful for quotes!

"""

@Language("Markdown")
const val markdownSample = """
    
    ![Blog Banner](https://blog.abnormallydriven.com/content/images/size/w2000/2018/10/blog_banner_2.jpg)
    
    In this post I want to take a look at the less sharp and more round DI framework that gets a lot of use in android projects: [Koin](https://insert-koin.io/) :)
Koin bills itself as being light weight with no magic. It doesn't do code generation and it doesn't do reflection. I was impressed with how easy it was to use and the simplicity of its API as I went through the process of creating the project to accompany this post. With all that said, let's start by looking at a project that doesnt use any DI to hopefully illustrate why you might want to use a DI framework at all. Once we've done that we'll introduce Koin to our project as our DI framework and use it to clean up the code and fix all of the little problems and annoyances we might have without a DI framework.
Our sample project will use the github API and pull down a list of publicly available repositories to present to the user in a list. You'll be able to tap on any of those repositories to be taken to a different screen to see some details about the repository and its owner. We'll make use of the viewmodel android architecture component library because it can be a little tricky/boiler platey to get your viewmodel injected in Dagger and I'd like to show how Koin handles the challenge. Besides, you should be using the viewmodel library anyway, its great!
I'll link to the entire project at the bottom of the article, but for the rest of the post let's focus on our repository list fragment. When our fragment comes to life we're gonna need a handful of objects to get the data to the screen for the user. Let's list them here
1. A view model
2. A RecyclerViewAdapter
3. Some RxJava Schedulers
4. A repository to get data from
Let's start with the repository. It'll be backed by a retrofit service and some in memory caches
```kotlin
class GithubRepository(private val githubApi: GithubApi) {
    private var repositoryListCache: List<RepositoryDto>? = null
    private var userCache: MutableMap<String, UserDetailsDto> = mutableMapOf()
    fun getRepositories(): Single<List<RepositoryDto>> {
        if (repositoryListCache != null) {
            return Single.just(repositoryListCache)
        }
        return githubApi.getRepositories()
            .doOnSuccess { repositoryListCache = it }
    }
    fun getUserDetails(username: String): Single<UserDetailsDto> {
        if (userCache[username] != null) {
            return Single.just(userCache[username])
        }
        return githubApi.getUserDetails(username)
            .doOnSuccess { userCache[username] = it }
    }
    fun getRepository(repositoryId: Int): Single<RepositoryDto> {
        return githubApi.getRepository(repositoryId)
    }
}
```
There's nothing too crazy in our repo, just some calls to the retrofit service which is its only dependency. Who needs DI right?? Well Let's look at what we'll need to do to create that github service real quick.
```kotlin
  
val gson = Gson()
val retrofitClient = Retrofit.Builder()
  .baseUrl("https://api.github.com/")
  .addConverterFactory(GsonConverterFactory.create(gson))
  .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
  .build()
val githubApi = retrofitClient.create(GithubApi::class.java)
```
As you can see we'd like to use Gson for our converter so first we'll need a gson object. Then because we like RxJava for how easy it makes threading (lol!) we're gonna want a call adapter factory using an RxJava scheduler. We'll use all those objects together with a retrofit builder to get a retrofit client and then we'll create an api object using that retrofit client.
OK so we've created a github api object in this snippet, which means creating a GithubRepository is now as easy as calling the constructor of the GithubRepository class and we're in business, but where should this code live? Remember, for now, we're in a world without DI so we're gonna have to stick this code some where in our fragment for use in displaying the list of repositories. Let's place it in our fragment's `onCreate` method for now.
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val gson = Gson()
        val retrofitClient = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
        val githubApi = retrofitClient.create(GithubApi::class.java)
        val githubRepository = GithubRepository(githubApi)
        
    }
```
Now that we've got a github repository we can give it to our viewmodel so that it can be used to load our data. We've got a viewmodel by the time onCreate is called because we set it up in `onAttach` like this:
```kotlin
override fun onAttach(context: Context) {
    super.onAttach(context)
    viewModel = ViewModelProviders.of(this)
       .get(RepositoryListFragmentViewModel::class.java)
    }
```
Now since we already have a viewModel we can't just pass our GitHubRepository object to it via a constructor parameter. That's a bummer since it has implications for testing and the usability of the view model. We'll have to add some kind of initialization method to our viewmodel that passes the GitHubRepository in as a parameter. Users of our viewmodel will just have to remember that they need to call that method before they use our viewmodel whether it be in application code or test code.
So with all of that in mind, here's what our `onCreate` method looks like now
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val gson = Gson()
            val retrofitClient = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build()
            val githubApi = retrofitClient.create(GithubApi::class.java)
            val githubRepository = GithubRepository(githubApi)
            viewModel.initialize(githubRepository, Schedulers.io(), AndroidSchedulers.mainThread())
        }
    }
```
We can now make use of our viewmodel in our fragment and call it's `fetchData` method. In onStart we'll subscribe to an observable that our view model exposes and eventually we'll get notified of a list of repositories that we need to display to the user. In order to do this we're gonna need a RecyclerView adapter. Our adapter is gonna need a DiffUtilCallback because I don't want to manually calculate changes in a RecyclerView ever again :) 
Let's create the adapter and the diffutil in `onViewCreated` right before we give our adapter to our recyclerview via the databinding object.
```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  val diffCallback = RepositoryDiffUtilItemCallback()
  val repositoryAdapter = RepositoryAdapter(diffCallback)
  
  binding.repositoriesRecyclerView.adapter = repositoryAdapter
}
```
We're not quite done yet though, you see we need to pass clicks from the adapter's items to some one who knows what to do with those clicks. So let's make sure we assign our adapter's item click call back as well
```kotlin
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
  val diffCallback = RepositoryDiffUtilItemCallback()
  val repositoryAdapter = RepositoryAdapter(diffCallback)
  val fragmentFactory = RepositoryDetailsFragmentFactory()
  repositoryAdapter.itemClickHandler = { repository -> 
    val fragment = fragmentFactory.create(repository)
    
    activity?.supportFragmentManager?.apply {
        beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack("details")
            .commit()
    }
  
  }
  
  binding.repositoriesRecyclerView.adapter = repositoryAdapter
}
```
Ok so now we have our click handler which will take us to our detail fragment when a user clicks on a repository in place. You'll notice we needed a fragment factory to create the detail fragment. That's so we can hide away the details involved in creating an argument bundle for that fragment in the factory object, but it also means that we have yet another line of code in our repository list fragment dedicated to object creation. This is one more line of code distracting you from the real reason behind this fragment's existence and one more line of code for you to read through everytime you come into this file to make a change or debug an issue. 
Worse still, everything we've done for our list fragment we'll have to also do for our detail fragment. It will also need a GitHubRepository in order to fetch its data which means that we either recreate a new instance of that object in the detail fragment, and lose out on that sweet in memory caching strategy that we implemented in the repository or we create some static `getInstance` method on the class that we need to make sure is thread safe and guarantees everybody in the app who calls that method gets the one and only repository instance!
Even worse, this kind of random object creation happening in every method of every object in your project is a sure fire way to make sure that you can never write a reliable set of unit tests for your project since you can't provide fakes or mocks during test. Hell when you first sit down to write the test you probably won't even be aware of what you'd need to fake or mock since your dependencies are scattered about the class in half a dozen places.
If only we had _something_ that could hide away the boiler plate-y object construction code, take care of guarantees regarding object instatiation and scope, and finally make testing easier by ensuring every object clearly defines and states its dependencies upfront in its constructor parameters.
It just so happens that a DI framework is that _something_ and now we can look at how we can introduce Koin to our project and let it take us to the promised land.
Koin is pretty easy to get started with, first we'll add a couple of dependencies to our build.gradle file
```groovy
implementation 'org.koin:koin-android:2.0.1'
implementation "org.koin:koin-androidx-viewmodel:2.0.1"
implementation 'org.koin:koin-androidx-scope:2.0.1'
```
Next we'll just need to go to our Application class's `onCreate` method call the `startKoin` method.
```kotlin
class RepoBrowserApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin{
            androidContext(this@RepoBrowserApp)
            modules(appModule)
        }
    }
}
```
Inside the startKoin block you'll notice we call androidContext() and pass our app instance into it. This is how we can give Koin application context that it can then use whenever it needs to create an object that requires some. 
The second line in the block is a call to `modules()` which can take either a single module or a list of them. We'll keep things simple in our app and just provide one, which we've named `appModule`. Where does this `appModule` live? and What does it look like?
I've chosen to place it in its own file but that isn't required by Koin at all.
The code looks like this:
```kotlin
val appModule = module {
    single<GithubApi> {
        val gson = Gson()
        val retrofitClient = Retrofit.Builder()
            .baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
            .build()
        val githubApi = retrofitClient.create(GithubApi::class.java)
        githubApi
    }
    single<GithubRepository>{
        GithubRepository(get())
    }
}
```
In our module we have two blocks of code that are preceeded by `single<SomeClass>`. This tells Koin that I'd like to use the following block of code to create an instance of `someClass` and that I'd like to koin to gurantee that there will only ever be one instance created. 
Our first block uses Gson and Retrofit to create an instance of our `GithubApi` which we then return from the block. 
The next part of our module looks very similar except that it uses `GithubRepository` as its generic arg and it simply calls our class's constructor with a single argument `get()`.
Now we know that when we wrote our class that we said its constructor should take an instance of `GithubApi` so what is this `get()` call doing? 
Well that is how we signal to koin that it needs to provide the dependency and since we've taught Koin how to create a `GithubApi` instance it will be able to go and grab that instance and use it to provide our repository object. 
Ok, so we've taught Koin how it can create a GithubApi and a GithubRepository, but how do we make use of this in our fragment?
If you recall in the fragment code above we were just creating these objects in the `onCreate` method of our fragment and then passing them to our viewmodel's initialize method. We want to remove all that boilerplate construction code from the fragment and just let Koin do it for us. 
Injecting these dependencies into our fragment is really easy with Koin as you can see in this code snippet:
```kotlin
class RepositoryListFragment : Fragment() {
    private val githubRepository: GithubRepository by inject()
```
We can delete all of that code we had in our `onCreate` method for creating a Gson object, then a Retrofit client, then a GithubApi object, and finally a GithubRepository with that single line of code at the top of our fragment. The by inject() tells Koin that it should go and get an instance of GithubRepository for our fragment.
At this point we know how to create singletons and inject them, but what about dependencies that we want scoped to just an instance of our fragment. Our `RepositoryAdapter` is just such an object, so let's take a look at the code we need to add to our module that will allow koin to provide this as a dependency scoped to our fragment
```kotlin
val appModule = module {
    //rest of declarations would be here
    scope(named<RepositoryListFragment>()){
        scoped { RepositoryDiffUtilItemCallback()}
        scoped { RepositoryAdapter(get())}
    }
```
First we create a scope block using our fragment class as a named generic argument and then inside the scope block we create two scoped dependencies. The first is our diff callback and then the second is our adapter who takes a diff callback as a constructor arg. 
Then back in our fragment we can inject our adapter in a manner similar to how we injected our repository.
```kotlin
class RepositoryListFragment : Fragment() {
    private val githubRepository: GithubRepository by inject()
    private val repositoryAdapter: RepositoryAdapter by currentScope.inject()
    
}
```
The only difference here is that since we have scoped this dependency we make use of currentScope.inject() when we declare that our fragment needs an instance of RepositoryAdapter.
So now we've seen how we can inject both singleton and scoped dependencies, but what about ViewModels? If you've used Dagger to provide constructor injected ViewModels you know there's some boilerplate code involving a factory and some module bindings. In Koin we have a lot less to do to inject our viewmodels. In fact as a long time dagger user, the way Koin handled viewmodel injection was actually my favorite feature.
```kotlin
val appModule = module {
    single<Scheduler>(named("ui")){ AndroidSchedulers.mainThread()}
    single<Scheduler>(named("computation")){ Schedulers.computation()}
    single<Scheduler>(named("io")){ Schedulers.io()}
    viewModel{ 
      RepositoryListFragmentViewModel(get(), get(named("io")), get(named("ui")))
    }
}
```
Let's break this code snippet into two pieces because there's actually two things we're learning about here at the same time. 
First we have 3 declarations involving some RxSchedulers. Our viewmodel takes two different schedulers as dependencies in its constructor declaration so we need to teach Koin how to provide them before we can create our viewmodel. 
Since we need to differentiate between the main, io, and computation schedulers we make use of Koin's qualified injection by including the `named()` call in our singleton declaration. The string we pass into `named()` will be used in any injection points where we need a scheduler. For example if we had a fragment where we wanted to inject the UI and IO scheduler, we would do something like this
```kotlin
private val uiScheduler: Scheduler by inject(named("ui"))
private val ioScheduler: Scheduler by inject(named("io"))
```
The second part of this code snippet simply makes a viewModel block and then inside of that block we create our viewmodel object and make use of get() and get(named()) calls to provide all of the dependencies our viewmodel uses.
That's it! We've now taught Koin how to inject a viewmodel for our fragment. Simple right?
Now let's look at how we inject it into our fragment
```kotlin
class RepositoryListFragment : Fragment() {
  private val fragmentViewModel: RepositoryListFragmentViewModel by viewModel()
    
}
```
If you thought providing the viewModel was simple, injecting it is even easier. We don't even need to make use of the `ViewModelProvider` class from the viewmodel library. All of that is taken care of for us by using `by viewModel()`
That's it for our introduction to Koin. We've covered how you can inject singletons, create and inject scoped dependencies, as well as how to provide contructor injected viewmodels in your android projects. This should be more than enough to get you started in dependency injection with Koin in your app!
You can find the entire project [here](https://github.com/abnormallydriven/koinrepobrowser) and as always I've included a links below that you might find useful as you continue to learn and try things out on your own with Koin.
# Useful Links
[Koin Reference](https://insert-koin.io/docs/2.0/documentation/reference/index.html)
[Android Getting Started](https://insert-koin.io/docs/2.0/getting-started/android/)
[Koin Project Github](https://github.com/InsertKoinIO/koin)
[Koin ViewModel](https://insert-koin.io/docs/2.0/getting-started/android-viewmodel/)
[Koin Scope](https://insert-koin.io/docs/2.0/getting-started/android-scope/)
[Article Project](https://github.com/abnormallydriven/koinrepobrowser)
"""
