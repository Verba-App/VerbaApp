package ru.nsu.ccfit.verba.core.network.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignInDto(
    val username: String,
    val password: String
)