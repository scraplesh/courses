@file:Suppress("PackageDirectoryMismatch")
object Deps {
    // region Plugins
    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val crashlyticsPlugin = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsPlugin}"
    const val googleServicesPlugin = "com.google.gms:google-services:${Versions.googleServicesPlugin}"
    const val hiltAndroidPlugin = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinGradlePlugin}"
    const val safeArgsPlugin = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidxNavigation}"
    // endregion

    // region Languages
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"
    // endregion

    // region JetBrains
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    // endregion

    // region Android
    const val androidxActivity = "androidx.appcompat:appcompat:${Versions.androidxActivity}"
    const val androidxAppCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
    const val androidxConstraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
    const val androidxCoreKtx = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
    const val androidxFragment = "androidx.fragment:fragment:${Versions.androidxFragment}"
    const val androidxFragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidxFragment}"
    const val androidxLifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidxLifecycle}"
    const val androidxLifecycleLiveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidxLifecycle}"
    const val androidxLifecycleReactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams:${Versions.androidxLifecycle}"
    const val androidxLifecycleRuntimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
    const val androidxLifecycleViewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    const val androidxNavigationFragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.androidxNavigation}"
    const val androidxNavigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.androidxNavigation}"
    const val androidxRecyclerView = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerView}"
    const val androidxSwipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.androidxSwipeRefreshLayout}"
    const val androidxViewPager2 = "androidx.viewpager2:viewpager2:${Versions.androidxViewPager2}"
    const val dagger = "com.google.dagger:dagger:${Versions.dagger}"
    const val daggerCompiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
    const val firebaseBom = "com.google.firebase:firebase-bom:${Versions.firebaseBom}"
    const val firebaseCrashlyticsKtx = "com.google.firebase:firebase-crashlytics-ktx"
    const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
    const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
    const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
    const val hiltAndroidCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltAndroidCompiler}"
    const val material = "com.google.android.material:material:${Versions.material}"
    const val roomRuntime = "androidx.room:room-runtime:${Versions.room}"
    const val roomCompiler = "androidx.room:room-compiler:${Versions.room}"
    const val roomKtx = "androidx.room:room-ktx:${Versions.room}"
    // endregion

    // region Libraries
    const val adapterDelegatesKotlinDslViewBinding = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.adapterDelegates}"
    const val corbind = "ru.ldralighieri.corbind:corbind:${Versions.corbind}"
    const val corbindAppCompat = "ru.ldralighieri.corbind:corbind-appcompat:${Versions.corbind}"
    const val corbindMaterial = "ru.ldralighieri.corbind:corbind-material:${Versions.corbind}"
    const val javaInject = "javax.inject:javax.inject:${Versions.javaInject}"
    const val okhttpLogging = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp}"
    // endregion
}
