plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:mvi"))
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.androidxLifecycleCommon)
    implementation(Deps.androidxLifecycleRuntimeKtx)
    implementation(Deps.androidxLifecycleLiveDataKtx)
    implementation(Deps.androidxLifecycleViewModelKtx)
    implementation(Deps.androidxViewPager2)
    implementation(Deps.corbind)
    implementation(Deps.coroutinesCore)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltViewModel)
    kapt(Deps.hiltAndroidCompiler)
    implementation(Deps.kotlinStdLib)
}
