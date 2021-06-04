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
    implementation(Deps.adapterDelegatesKotlinDslViewBinding)
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.androidxLifecycleLiveDataKtx)
    implementation(Deps.androidxRecyclerView)
    implementation(Deps.corbind)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    implementation(Deps.hiltViewModel)
    kapt(Deps.hiltAndroidCompiler)
}
