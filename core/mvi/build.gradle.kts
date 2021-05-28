plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(project(":core:common"))
    implementation(Deps.androidxAppCompat)
    implementation(Deps.androidxLifecycleCommon)
    implementation(Deps.androidxLifecycleLiveDataKtx)
    implementation(Deps.androidxLifecycleViewModelKtx)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.coroutinesCore)
}
