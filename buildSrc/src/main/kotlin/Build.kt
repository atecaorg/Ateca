@file:Suppress("SpellCheckingInspection")

/**
 * Created by dronpascal on 26.04.2022.
 */
object Build {
    /**
     * To update version of gradle plugin change [androidBuildToolsVersion]
     * and update Gradle version in gradle-wrapper.properties.
     * @see <a href="https://developer.android.com/studio/releases/gradle-plugin#updating-gradle">Gradle and AGP versions contract</a>
     */
    private const val androidBuildToolsVersion = "7.2.0"
    const val androidBuildTools = "com.android.tools.build:gradle:$androidBuildToolsVersion"

    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"

    const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.version}"

    const val hiltAndroid = "com.google.dagger:hilt-android-gradle-plugin:${Hilt.version}"

    private const val googleServicesVersion = "4.3.10"
    const val googleServices = "com.google.gms:google-services:$googleServicesVersion"

    private const val crashlyticsVersion = "2.9.0"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:$crashlyticsVersion"
}