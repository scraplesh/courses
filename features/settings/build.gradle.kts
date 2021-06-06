plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:mvi"))
    implementation(project(":core:mvi-android"))
    implementation(project(":core:uikit"))
    implementation(project(":domain"))
    implementation(Deps.Android.constraintLayout)
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Android.fragmentKtx)
    implementation(Deps.Dagger.hiltAndroid)
    kapt(Deps.Dagger.hiltCompiler)
    implementation(Deps.Dagger.hiltViewModel)
    kapt(Deps.Dagger.hiltAndroidCompiler)
    implementation(Deps.Libraries.corbind)
    implementation(Deps.Lifecycle.common)
    implementation(Deps.Lifecycle.liveDataKtx)
    implementation(Deps.Lifecycle.runtimeKtx)
    implementation(Deps.Lifecycle.viewModelKtx)
}
