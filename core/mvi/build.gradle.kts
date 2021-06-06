plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(Deps.Coroutines.android)
    implementation(Deps.Coroutines.core)
}
