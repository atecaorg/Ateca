buildscript {

    repositories {
        google()
        mavenCentral()
    }

    // Top-level build file where you can add configuration options common to all sub-projects/modules.
    dependencies {
        classpath(Build.androidBuildTools)
        classpath(Build.kotlinGradlePlugin)
        classpath(Build.kotlinSerialization)
        classpath(Build.hiltAndroid)
        classpath(Build.googleServices)
        classpath(Build.crashlytics)
    }

}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}