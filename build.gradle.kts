import com.android.build.api.dsl.CommonExtension
import com.android.build.gradle.TestedExtension
import org.jetbrains.kotlin.gradle.internal.AndroidExtensionsExtension
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        mavenCentral()
        google()
    }
    dependencies {
        classpath(Deps.androidGradlePlugin)
        classpath(Deps.kotlinGradlePlugin)
        classpath(Deps.hiltAndroidPlugin)
        classpath(Deps.safeArgsPlugin)
    }
}
allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

subprojects {
    addKotlinCompilerFlags()
    forceDependencyVersions()
    afterEvaluate {
        extensions.findByType(CommonExtension::class.java)
            ?.apply {
                buildFeatures {
                    viewBinding = true
                }
            }
        extensions.findByType(TestedExtension::class.java)
            ?.apply {
                enableExperimentalKotlinExtensions()
                defaultConfig {
                    versionCode = AndroidConfig.versionCode
                    versionName = AndroidConfig.versionName
                    minSdkVersion(AndroidConfig.minSdkVersion)
                    targetSdkVersion(AndroidConfig.minSdkVersion)
                }

                compileSdkVersion(AndroidConfig.compileSdkVersion)

                sourceSets.forEach { sourceSet ->
                    sourceSet.java.srcDir("src/${sourceSet.name}/kotlin")
                    sourceSet.java.srcDir("src/${sourceSet.name}/kotlinx")
                }

                with(compileOptions) {
                    sourceCompatibility = Versions.java
                    targetCompatibility = Versions.java
                }

                packagingOptions {
                    exclude("META-INF/DEPENDENCIES")
                    exclude("META-INF/AL2.0")
                    exclude("META-INF/LGPL2.1")
                }
            }
    }
}

fun Project.addKotlinCompilerFlags() {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions {
            jvmTarget = "1.8"
            kotlinOptions.freeCompilerArgs += listOf(
                "-Xuse-experimental=kotlinx.coroutines.ExperimentalCoroutinesApi"
            )
        }
    }
}

fun Project.forceDependencyVersions() {
    configurations.all {
        resolutionStrategy {
            force(Deps.kotlinStdLib)
        }
    }
}

fun Project.enableExperimentalKotlinExtensions() {
    extensions.findByType(AndroidExtensionsExtension::class)?.isExperimental = true
}

task("clean") {
    delete(rootProject.buildDir)
}
