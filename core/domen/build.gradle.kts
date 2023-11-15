plugins {
    alias(libs.plugins.verba.android.library)
    alias(libs.plugins.verba.android.library.jacoco)
    kotlin("kapt")
}

android {
    namespace = "ru.nsu.ccfit.verba.domen"
}

dependencies {

    implementation(projects.core.data)
    implementation(projects.core.model)
    implementation(libs.hilt.android)
    implementation(libs.kotlinx.coroutines.android)

    kapt(libs.hilt.compiler)

    testImplementation(projects.core.testing)
}