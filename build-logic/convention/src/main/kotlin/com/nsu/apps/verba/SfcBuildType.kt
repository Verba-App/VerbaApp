package com.nsu.apps.verba

/**
 * This is shared between :app and :benchmarks module to provide configurations type safety.
 */
enum class SfcBuildType(val applicationIdSuffix: String? = null) {
    DEBUG(".debug"),
    RELEASE,
    BENCHMARK(".benchmark")
}
