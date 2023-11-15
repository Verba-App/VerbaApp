plugins {
    alias(libs.plugins.verba.android.library)
    alias(libs.plugins.verba.android.library.jacoco)
    alias(libs.plugins.verba.android.hilt)
    id("kotlinx-serialization")
}

android {
    namespace = "ru.nsu.ccfit.verba.core.network"

    buildFeatures {
        buildConfig = true
    }
    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
}

dependencies {
    implementation(projects.core.common)

    implementation(libs.kotlinx.serialization.json)

    implementation(libs.ktor.android)
    implementation(libs.ktor.serialization)
    implementation(libs.ktor.content.negotiation)
    implementation(libs.ktor.serialization.kotlinx.json)
    implementation(libs.ktor.logging.jvm)

}