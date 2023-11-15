plugins {
    alias(libs.plugins.verba.android.library)
    alias(libs.plugins.verba.android.library.jacoco)
    alias(libs.plugins.verba.android.hilt)
}

android {
    namespace = "ru.nsu.ccfit.verba.core.data"

}

dependencies {
    implementation(projects.core.model)
    implementation(projects.core.network)

    implementation(libs.androidx.core.ktx)
    implementation(libs.kotlinx.coroutines.android)
    implementation(libs.kotlinx.serialization.json)
}