plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":domain"))
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Dagger.dagger)
    kapt(Deps.Dagger.compiler)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.ktx)
    implementation(Deps.Room.runtime)
}
