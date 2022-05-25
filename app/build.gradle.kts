import com.google.protobuf.gradle.*

plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("com.google.protobuf")
}

android {
    compileSdk = Android.compileSdk
    buildToolsVersion = Android.buildTools

    defaultConfig {
        applicationId = Android.appId
        minSdk = Android.minSdk
        targetSdk = Android.targetSdk
        versionCode = Android.versionCode
        versionName = Android.versionName
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("debug.keystore")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
        create("release") {
            storeFile = rootProject.file("release.keystore")
            storePassword = "keystore password"
            keyAlias = "release"
            keyPassword = "release key password"
        }
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isCrunchPngs = false
            signingConfig = signingConfigs.getByName("debug")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
        viewBinding = true

        // Disable unused AGP features
        aidl = false
        renderScript = false
        resValues = false
        shaders = false
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.version
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"

        @Suppress("SuspiciousCollectionReassignment")
        freeCompilerArgs += "-opt-in=kotlin.RequiresOptIn"
    }

    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    kapt {
        // Allow references to generated code
        correctErrorTypes = true
        useBuildCache = true
    }
}

dependencies {
    // Google
    implementation(Google.Material.material3)

    // Kotlin
    implementation(KotlinX.Coroutines.core)
    implementation(KotlinX.Coroutines.android)
    implementation(KotlinX.Json.json)

    // Lifecycle
    implementation(AndroidX.Lifecycle.livedata)
    implementation(AndroidX.Lifecycle.viewmodel)
    implementation(AndroidX.Lifecycle.viewModelCompose)

    // Hilt
    implementation(Hilt.android)
    kapt(Hilt.compiler)

    // Compose
    implementation(Compose.foundation)
    implementation(Compose.layout)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.Material3.material3)
    implementation(Compose.materialIconsExtended)
    implementation(Compose.uiUtil)
    implementation(Compose.viewBinding)
    implementation(Compose.runtime)
    implementation(Compose.runtimeLivedata)
    implementation(Compose.activity)
    debugImplementation(Compose.toolingDebug)
    implementation(Compose.toolingPreview)

    // Compose/hiltNavigation
    implementation(Compose.hiltNavigationCompose)

    // Accompanist
    implementation(Accompanist.animations)
    implementation(Accompanist.permissions)
    implementation(Accompanist.systemUiController)

    // Navigation
    implementation(Compose.navigation)
    implementation(AndroidX.Navigation.fragment)
    implementation(AndroidX.Navigation.uiKtx)

    // Coil
    implementation(Coil.coil)

    // Room
    implementation(AndroidX.Room.runtime)
    implementation(AndroidX.Room.ktx)
    kapt(AndroidX.Room.compiler)

    // Retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofitConverterMoshi)
    implementation(Retrofit.retrofitConverterSerialization)
    implementation(Retrofit.retrofitLoggingInterceptor)

    // DataStore
    implementation(AndroidX.DataStore.dataStore)
    implementation(Protobuf.JavaLite.javalite)
    implementation(AndroidX.Preference.preference)

    // Markdown processor
    implementation(Markdown.flexmark)

    // Window
    implementation(AndroidX.Window.window)

    // Test
    androidTestImplementation(Junit.junit4)

    // Test/androidx
    androidTestImplementation(AndroidX.Test.core)
    androidTestImplementation(AndroidX.Test.espressoCore)
    androidTestImplementation(AndroidX.Test.rules)
    androidTestImplementation(AndroidX.Test.Ext.junit)

    // Test/coroutines
    testImplementation(KotlinX.Coroutines.test)

    // Test/compose
    androidTestImplementation(Compose.Test.test)
    androidTestImplementation(Compose.Test.uiTestJunit4)
    debugImplementation(Compose.Test.uiTestManifest)

    // Test/hilt
    androidTestImplementation(Hilt.Test.hiltAndroidTesting)
    kaptAndroidTest(Hilt.compiler)

    // androidx.test is forcing JUnit, 4.12. This forces it to use 4.13
    configurations.configureEach {
        resolutionStrategy {
            force(Junit.junit4)
        }
    }
}

protobuf.protobuf.run {

    protoc {
        artifact = Protoc.protoc
    }

    generateProtoTasks {
        all().forEach { task ->
            task.builtins {
                id("java") {
                    option("lite")
                }
            }
        }
    }
}