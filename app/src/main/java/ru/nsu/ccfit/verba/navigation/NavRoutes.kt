package ru.nsu.ccfit.verba.navigation

sealed class NavRoutes(internal open val path: String) {

    data object Auth : NavRoutes("auth")
    data object Groups : NavRoutes("groups")

    data object DetailsGroup : NavRoutes("details/group/{$DETAILS_ID_KEY}") {
        fun build(id: String): String =
            path.replace("{$DETAILS_ID_KEY}", id)
    }

    companion object {
        const val DETAILS_ID_KEY: String = "id"
    }
}
