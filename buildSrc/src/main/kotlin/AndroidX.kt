/**
 * Created by dronpascal on 26.04.2022.
 */
object AndroidX {
    /**
     * @see <a href="https://developer.android.com/jetpack/androidx/releases/core"> Core Doc</a>
     */
    private const val coreVersion = "1.7.0"
    const val coreKtx = "androidx.core:core-ktx:$coreVersion"

    object Lifecycle {
        /**
         * @see <a href="https://developer.android.com/jetpack/androidx/releases/lifecycle"> Lifecycle Doc</a>
         */
        private const val lifecycleKtxVersion = "2.4.1"
        const val lifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx::$lifecycleKtxVersion"
        const val lifecycleVmComposeKtx = "androidx.lifecycle:lifecycle-viewmodel-compose:$lifecycleKtxVersion"
    }

    object DataStore {
        /**
         * @see <a href="https://developer.android.com/topic/libraries/architecture/datastore"> DataStore Doc</a>
         */
        private const val dataStoreVersion = "1.0.0"
        const val dataStore = "androidx.datastore:datastore-preferences:$dataStoreVersion"
    }

    object Room {
        /**
         * @see <a href="https://developer.android.com/jetpack/androidx/releases/room"> Room Doc</a>
         */
        private const val roomVersion = "2.4.2"
        const val roomRuntime = "androidx.room:room-runtime:$roomVersion"
        const val roomCompiler = "androidx.room:room-compiler:$roomVersion"
        const val roomKtx = "androidx.room:room-ktx:$roomVersion"
    }
}