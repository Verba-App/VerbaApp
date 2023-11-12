package ru.nsu.ccfit.verba.navigation

sealed class NavRoutes(internal open val path: String) {

    data object Auth : NavRoutes("auth")

}
