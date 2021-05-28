plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:mvi"))
    implementation(project(":domain"))
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.androidxCoreKtx)
    implementation(Deps.androidxFragment)
    implementation(Deps.androidxLifecycleCommon)
    implementation(Deps.androidxLifecycleLiveDataKtx)
    implementation(Deps.androidxLifecycleRuntimeKtx)
    implementation(Deps.androidxLifecycleViewModelKtx)
    implementation(Deps.corbind)
}
