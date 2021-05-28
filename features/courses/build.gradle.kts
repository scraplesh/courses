plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:mvi"))
    implementation(project(":domain"))
    implementation(Deps.adapterDelegatesKotlinDslViewBinding)
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.androidxCoreKtx)
    implementation(Deps.androidxLifecycleCommon)
    implementation(Deps.androidxLifecycleLiveDataKtx)
    implementation(Deps.androidxRecyclerView)
    implementation(Deps.corbind)
    implementation(Deps.material)
}
