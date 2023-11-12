plugins {
    alias(libs.plugins.verba.android.feature)
    alias(libs.plugins.verba.android.library.compose)
    alias(libs.plugins.verba.android.library.jacoco)
}
android {
    namespace = "ru.nsu.ccfit.verba.feature"
}
dependencies {
    implementation(libs.compose.material3)
}
