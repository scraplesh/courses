plugins {
    id("com.android.library")
    id("kotlin-android")
}

dependencies {
    implementation(project(":core:mvi"))
    implementation(Deps.Lifecycle.viewModelKtx)
    implementation(Deps.Coroutines.android)
    implementation(Deps.Coroutines.core)
}
