plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.coroutinesCore)
}
