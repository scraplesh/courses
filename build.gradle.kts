buildscript {
    repositories {
        jcenter()
        google()
    }
    dependencies {
        classpath(Deps.androidGradlePlugin)
        classpath(Deps.kotlinGradlePlugin)
        classpath(Deps.safeArgsPlugin)
        classpath(Deps.hiltAndroidPlugin)
    }
}
allprojects {
    repositories {
        jcenter()
        google()
    }

    tasks.withType(org.jetbrains.kotlin.gradle.tasks.KotlinCompile::class.java).all {
        kotlinOptions.freeCompilerArgs += listOf(
            "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
        )
    }
}

task("clean") {
    delete(rootProject.buildDir)
}
