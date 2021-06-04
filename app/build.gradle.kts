plugins {
    id("com.android.application")
    kotlin("android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdkVersion(AndroidConfig.compileSdkVersion)

    defaultConfig {
        applicationId = AndroidConfig.applicationId
        minSdkVersion(AndroidConfig.minSdkVersion)
        targetSdkVersion(AndroidConfig.minSdkVersion)
        versionCode = AndroidConfig.versionCode
        versionName = AndroidConfig.versionName

        buildConfigField("String", "productionFlavorName", "\"${Environment.Production.value}\"")
    }

    signingConfigs {
        getByName("debug") {
            storeFile = file("../signing/debug.jks")
            storePassword = "android"
            keyAlias = "androiddebugkey"
            keyPassword = "android"
        }
    }

    buildTypes {
        getByName("release") {
            signingConfig = signingConfigs.getByName("debug")
            isMinifyEnabled = true
            isShrinkResources = true
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
        }
    }

    buildFeatures {
        viewBinding = true
    }

    compileOptions {
        sourceCompatibility = Versions.java
        targetCompatibility = Versions.java
    }

    kotlinOptions {
        jvmTarget = Versions.java.toString()
    }

    sourceSets.getByName("main") {
        java.srcDir("src/main/kotlin")
        java.srcDir("src/main/kotlinx")
    }
    sourceSets.getByName("debug") {
        java.srcDir("src/debug/kotlin")
    }
    sourceSets.getByName("release") {
        java.srcDir("src/release/kotlin")
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:navigation"))
    implementation(project(":core:mvi"))
    implementation(project(":core:mvi-android"))
    implementation(project(":core:uikit"))
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":features:course"))
    implementation(project(":features:coursefinish"))
    implementation(project(":features:courses"))
    implementation(project(":features:onboarding"))
    implementation(project(":features:settings"))
    implementation(project(":features:signin"))
    implementation(project(":features:signup"))
    implementation(project(":features:timemanagement"))
    implementation(Deps.androidxActivity)
    implementation(Deps.androidxAppCompat)
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.androidxNavigationFragmentKtx)
    implementation(Deps.androidxNavigationUiKtx)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.kotlinStdLib)
}
