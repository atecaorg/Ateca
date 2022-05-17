plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlinx-serialization")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
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

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
            isCrunchPngs = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/proguard-rules.pro"
            )
        }
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "$project.rootDir/proguard-rules.pro"
            )
        }
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Compose.composeVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    packagingOptions {
        resources.excludes.add("META-INF/AL2.0")
        resources.excludes.add("META-INF/LGPL2.1")
    }

    kapt {
        // Allow references to generated code
        correctErrorTypes = true
    }
}

dependencies {
    // Kotlin
    implementation(KotlinX.coroutinesCore)
    implementation(KotlinX.coroutinesAndroid)

    // Android extensions
    implementation(AndroidX.coreKtx)
    // Navigation library includes RuntimeKtx by default
    // implementation(AndroidX.Lifecycle.lifecycleRuntimeKtx)
    implementation(AndroidX.Lifecycle.lifecycleVmComposeKtx)

    // Hilt
    implementation(Hilt.android)
    kapt(Hilt.compiler)
    // Compose
    implementation(Compose.activity)
    implementation(Compose.runtime)
    implementation(Compose.livedata)
    implementation(Compose.ui)
    implementation(Compose.material)
    implementation(Compose.icons)
    implementation(Compose.toolingPreview)
    debugImplementation(Compose.toolingDebug)
    // Compose/hiltNavigation
    implementation(Compose.hiltNavigationCompose)
    // Accompanist
    implementation(Accompanist.animations)
    implementation(Accompanist.insets)

    // Navigation
    implementation(Compose.navigation)

    // Coil
    implementation(Coil.coil)

    // Room
    implementation(AndroidX.Room.roomRuntime)
    implementation(AndroidX.Room.roomKtx)
    kapt(AndroidX.Room.roomCompiler)

    // Retrofit
    implementation(Retrofit.retrofit)
    implementation(Retrofit.retrofitConverterMoshi)
    implementation(Retrofit.retrofitConverterSerialization)
    implementation(Retrofit.retrofitLoggingInterceptor)

    // DataStore
    implementation(AndroidX.DataStore.dataStore)

    // Test
    androidTestImplementation(Junit.junit4)
    // Test/compose
    androidTestImplementation(Compose.Test.uiTestJunit4)
    debugImplementation(Compose.Test.uiTestManifest)
    // Test/hilt
    androidTestImplementation(Hilt.Test.hiltAndroidTesting)
    kaptAndroidTest(Hilt.compiler)

}