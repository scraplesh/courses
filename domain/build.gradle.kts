plugins {
    id("java-library")
    id("kotlin")
    id("kotlin-kapt")
}

java.sourceCompatibility = JavaVersion.VERSION_1_8
java.targetCompatibility = JavaVersion.VERSION_1_8

dependencies {
    implementation(Deps.kotlinStdLib)
    implementation(Deps.coroutinesCore)
    implementation(Deps.dagger)
    kapt(Deps.daggerCompiler)
}
