plugins {
    alias(libs.plugins.verba.android.feature)
    alias(libs.plugins.verba.android.library.compose)
    alias(libs.plugins.verba.android.library.jacoco)
}

android {
    namespace = "ru.nsu.ccfit.verba.feature.detailsgroup"
}

dependencies {
    implementation(projects.feature.common)
    implementation(libs.androidx.compose.material)
}