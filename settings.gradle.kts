pluginManagement {
    includeBuild("build-logic")
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven { url = uri("https://jitpack.io") }
    }
}

rootProject.name = "verba-app"
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
include(":app")

include(":feature:auth")
include(":feature:common")
include(":feature:groups")
include(":feature:detailsgroup")

include(":core:ui")
include(":core:model")
include(":core:data")
include(":core:common")
include(":core:network")
include(":core:designsystem")
include(":core:testing")
include(":core:domen")
