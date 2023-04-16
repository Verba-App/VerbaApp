package ru.nsu.ccfit.verbaapp.core.auth.domen

data class AuthState(
    val isLoading: Boolean = false,
    val signUpUsername: String = "",
    val signUpEmail: String = "",
    val signUpPassword: String = "",
    val signInUsername: String = "",
    val signInPassword: String = ""
)
