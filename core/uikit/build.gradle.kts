plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

dependencies {
    implementation(project(":domain"))
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.material)
}
