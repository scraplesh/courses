@file:Suppress("PackageDirectoryMismatch")

object Plugins {
    const val androidGradle = "com.android.tools.build:gradle:${Versions.androidGradlePlugin}"
    const val hiltAndroid = "com.google.dagger:hilt-android-gradle-plugin:${Versions.dagger}"
    const val kotlinGradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidxNavigation}"
}