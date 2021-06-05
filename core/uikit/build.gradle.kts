plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    sourceSets.getByName("main") {
        res.srcDir("src/main/res/drawable/widgets")
    }
}

dependencies {
    implementation(Deps.androidxConstraintLayout)
    implementation(Deps.material)
}
