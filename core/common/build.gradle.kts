plugins {
    alias(libs.plugins.verba.android.library)
    alias(libs.plugins.verba.android.library.jacoco)
    alias(libs.plugins.verba.android.hilt)
}

android {
    namespace = "ru.nsu.ccfit.verba.core.common"
}

dependencies {
    implementation(libs.kotlinx.coroutines.android)
}