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
    implementation(project(":domain"))
    implementation(Deps.adapterDelegatesKotlinDslViewBinding)
    implementation(Deps.androidxAppCompat)
    implementation(Deps.androidxFragmentKtx)
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.androidxCoreKtx)
    implementation(Deps.androidxLifecycleCommon)
    implementation(Deps.androidxLifecycleLiveDataKtx)
    implementation(Deps.androidxRecyclerView)
    implementation(Deps.corbind)
    implementation(Deps.hiltAndroid)
    kapt(Deps.hiltCompiler)
    kapt(Deps.hiltAndroidCompiler)
    implementation(Deps.javaInject)
    implementation(Deps.material)
}
