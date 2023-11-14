plugins {
    alias(libs.plugins.verba.android.application)
    alias(libs.plugins.verba.android.application.compose)
    alias(libs.plugins.verba.android.application.flavors)
    alias(libs.plugins.verba.android.application.jacoco)
    alias(libs.plugins.verba.android.hilt)
}

android {
    namespace = "ru.nsu.ccfit.verba"


    defaultConfig {
        applicationId = "ru.nsu.ccfit.verba"


        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/*"
        }
    }
}

dependencies {
    implementation(projects.feature.auth)
    implementation(projects.feature.groups)
    implementation(projects.feature.detailsgroup)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui.tooling.preview)
    implementation(libs.androidx.compose.material)

    androidTestImplementation(projects.core.testing)
    testImplementation(projects.core.testing)
}