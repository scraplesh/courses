plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":domain"))
    implementation(Deps.androidxCoreKtx)
    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)
    kapt(Deps.roomCompiler)
    implementation(Deps.roomKtx)
    implementation(Deps.roomRuntime)
}
