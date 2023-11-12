plugins {
    alias(libs.plugins.verba.android.library)
    alias(libs.plugins.verba.android.library.compose)
    alias(libs.plugins.verba.android.library.jacoco)
}

android {
    namespace = "ru.nsu.ccfit.verba.core.designsystem"

}

dependencies {
    api(libs.androidx.compose.foundation)
    api(libs.androidx.compose.foundation.layout)
    api(libs.androidx.compose.material.iconsExtended)
    api(libs.androidx.compose.material)
    api(libs.androidx.compose.runtime)
    api(libs.androidx.compose.ui.tooling.preview)
    api(libs.androidx.compose.ui.util)

    implementation(libs.androidx.core.ktx)
    implementation(libs.coil.kt.compose)
}