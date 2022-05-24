@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 26.04.2022.
 */
object KotlinX {

    object Coroutines {
        /**
         * @see <a href="https://github.com/Kotlin/kotlinx.coroutines">Coroutines Doc</a>
         */
        private const val version = "1.6.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Json {
        private const val version = "1.3.2"

        // KotlinX Serialization
        const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:$version"
    }

}