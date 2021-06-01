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
    implementation(project(":domain"))
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.androidxCoreKtx)
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.androidxLifecycleCommon)
    implementation(Deps.androidxLifecycleLiveDataKtx)
    implementation(Deps.androidxLifecycleRuntimeKtx)
    implementation(Deps.androidxLifecycleViewModelKtx)
    implementation(Deps.corbind)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltViewModel)
    kapt(Deps.hiltAndroidCompiler)
}
