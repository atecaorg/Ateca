@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 26.04.2022.
 */
object AndroidX {
    /**
     * @see <a href="https://developer.android.com/jetpack/androidx/releases/core"> Core Doc</a>
     */
    private const val version = "1.7.0"
    const val coreKtx = "androidx.core:core-ktx:$version"

    object Lifecycle {
        /**
         * @see <a href="https://developer.android.com/jetpack/androidx/releases/lifecycle"> Lifecycle Doc</a>
         */
        private const val version = "2.4.1"
        const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx::$version"
        const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
        const val livedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
        const val viewmodel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
        const val viewModelCompose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
    }

    object DataStore {
        /**
         * @see <a href="https://developer.android.com/topic/libraries/architecture/datastore"> DataStore Doc</a>
         */
        private const val version = "1.0.0"
        const val dataStore = "androidx.datastore:datastore-preferences:$version"
    }

    object Preference {
        private const val version = "1.2.0"
        const val preference = "androidx.preference:preference-ktx:$version"
    }

    object Room {
        /**
         * @see <a href="https://developer.android.com/jetpack/androidx/releases/room"> Room Doc</a>
         */
        private const val version = "2.4.2"
        const val runtime = "androidx.room:room-runtime:$version"
        const val compiler = "androidx.room:room-compiler:$version"
        const val ktx = "androidx.room:room-ktx:$version"
    }

    object Navigation {
        private const val version = "2.4.2"
        const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:$version"
    }

    object Window {
        private const val version = "1.0.0"
        const val window = "androidx.window:window:$version"
    }

    object Test {
        private const val version = "1.4.0"
        const val core = "androidx.test:core:$version"
        const val rules = "androidx.test:rules:$version"

        object Ext {
            private const val version = "1.1.2"
            const val junit = "androidx.test.ext:junit-ktx:$version"
        }

        const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
    }
}