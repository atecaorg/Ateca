/**
 * Created by dronpascal on 26.04.2022.
 */
object Hilt {
    /**
     * @see <a href=" https://developer.android.com/training/dependency-injection/hilt-android"> Hilt Doc</a>
     */
    const val hiltVersion = "2.41"
    const val android = "com.google.dagger:hilt-android:$hiltVersion"
    const val compiler = "com.google.dagger:hilt-android-compiler:$hiltVersion"

    object Test {
        const val hiltAndroidTesting = "com.google.dagger:hilt-android-testing:${hiltVersion}"
    }
}