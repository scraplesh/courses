plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(project(":core:mvi"))
    implementation(Deps.androidxLifecycleViewModelKtx)
    implementation(Deps.coroutinesAndroid)
    implementation(Deps.coroutinesCore)
}
