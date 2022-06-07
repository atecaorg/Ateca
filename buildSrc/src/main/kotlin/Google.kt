@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 18.05.2022.
 */
object Google {

    object Material {
        private const val version = "1.6.0"
        const val material3 = "com.google.android.material:material:$version"
    }

    /**
     * @see <a href="https://firebase.google.com/docs/android/setup#available-libraries">Firebase</a>
     */
    object Firebase {
        private const val version = "30.1.0"
        const val core = "com.google.firebase:firebase-bom:$version"
        const val analyticsKtx = "com.google.firebase:firebase-analytics-ktx"
        const val crashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
    }

}