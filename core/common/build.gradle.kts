plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-android-extensions")
}

dependencies {
    implementation(project(":domain"))
    implementation(Deps.Android.constraintLayout)
    implementation(Deps.Android.fragmentKtx)
    implementation(Deps.Android.material)
}
