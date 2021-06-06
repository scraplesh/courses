plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    sourceSets.getByName("main") {
        res.srcDir("src/main/res/drawable/reviews")
        res.srcDir("src/main/res/drawable/courses")
    }
}

dependencies {
    implementation(project(":core:common"))
    implementation(project(":core:mvi"))
    implementation(project(":core:mvi-android"))
    implementation(project(":core:navigation"))
    implementation(project(":core:uikit"))
    implementation(project(":domain"))
    implementation(Deps.Libraries.adapterDelegatesKotlinDslViewBinding)
    implementation(Deps.Android.appCompat)
    implementation(Deps.Android.constraintLayout)
    implementation(Deps.Android.coreKtx)
    implementation(Deps.Android.fragmentKtx)
    implementation(Deps.Android.material)
    implementation(Deps.Android.recyclerView)
    implementation(Deps.Libraries.corbind)
    implementation(Deps.Lifecycle.common)
    implementation(Deps.Lifecycle.liveDataKtx)
    implementation(Deps.Dagger.hiltAndroid)
    kapt(Deps.Dagger.hiltCompiler)
    kapt(Deps.Dagger.hiltAndroidCompiler)
}
