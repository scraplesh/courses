@file:Suppress("PackageDirectoryMismatch")

// @formatter:off
object Deps {
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}"

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    }

    object Android {
        const val appCompat = "androidx.appcompat:appcompat:${Versions.androidxAppCompat}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.androidxConstraintLayout}"
        const val coreKtx = "androidx.core:core-ktx:${Versions.androidxCoreKtx}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.androidxFragment}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.androidxRecyclerView}"
        const val viewPager2 = "androidx.viewpager2:viewpager2:${Versions.androidxViewPager2}"
        const val material = "com.google.android.material:material:${Versions.material}"
    }

    object Navigation {
        const val fragmentKtx = "androidx.navigation:navigation-fragment-ktx:${Versions.androidxNavigation}"
        const val uiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.androidxNavigation}"
    }

    object Lifecycle {
        const val common = "androidx.lifecycle:lifecycle-common-java8:${Versions.androidxLifecycle}"
        const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidxLifecycle}"
        const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.androidxLifecycle}"
        const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidxLifecycle}"
    }

    object Dagger {
        const val hiltAndroid = "com.google.dagger:hilt-android:${Versions.dagger}"
        const val hiltCompiler = "com.google.dagger:hilt-compiler:${Versions.dagger}"
        const val hiltViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hiltViewModel}"
        const val hiltAndroidCompiler = "androidx.hilt:hilt-compiler:${Versions.hiltAndroidCompiler}"
    }

    object Room {
        const val runtime = "androidx.room:room-runtime:${Versions.room}"
        const val compiler = "androidx.room:room-compiler:${Versions.room}"
        const val ktx = "androidx.room:room-ktx:${Versions.room}"
    }

    object Libraries {
        const val adapterDelegatesKotlinDslViewBinding = "com.hannesdorfmann:adapterdelegates4-kotlin-dsl-viewbinding:${Versions.adapterDelegates}"
        const val corbind = "ru.ldralighieri.corbind:corbind:${Versions.corbind}"
        const val corbindAppCompat = "ru.ldralighieri.corbind:corbind-appcompat:${Versions.corbind}"
        const val corbindMaterial = "ru.ldralighieri.corbind:corbind-material:${Versions.corbind}"
        const val javaInject = "javax.inject:javax.inject:${Versions.javaInject}"
        const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    }
}
// @formatter:on
