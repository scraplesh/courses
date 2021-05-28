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
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.androidxLifecycleLiveDataKtx)
    implementation(Deps.corbind)
    implementation(Deps.corbindAppCompat)
    implementation(Deps.material)
}
