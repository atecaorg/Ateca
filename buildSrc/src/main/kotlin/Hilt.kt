@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 26.04.2022.
 */
object Hilt {
    /**
     * @see <a href="https://developer.android.com/training/dependency-injection/hilt-android">Hilt Doc</a>
     */
    const val version = "2.41"
    const val android = "com.google.dagger:hilt-android:$version"
    const val compiler = "com.google.dagger:hilt-android-compiler:$version"

    object Test {
        const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${version}"
    }
}