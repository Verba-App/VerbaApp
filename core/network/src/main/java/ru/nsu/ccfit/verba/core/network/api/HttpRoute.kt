package ru.nsu.ccfit.verba.core.network.api

object HttpRoute {
    private const val api = ""

    const val signIn =
        "$api/auth/token"

    const val signUp =
        "$api/auth/register"

    const val authenticate =
        "$api/auth/register"
}