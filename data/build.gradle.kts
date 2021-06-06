plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":domain"))
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Libraries.javaInject)
    implementation(Deps.Libraries.retrofit)
    kapt(Deps.Room.compiler)
    implementation(Deps.Room.ktx)
    implementation(Deps.Room.runtime)
}
