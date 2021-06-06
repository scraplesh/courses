plugins {
    id("java-library")
    id("kotlin")
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

dependencies {
    implementation(Deps.kotlinStdLib)
    implementation(Deps.Coroutines.core)
}
