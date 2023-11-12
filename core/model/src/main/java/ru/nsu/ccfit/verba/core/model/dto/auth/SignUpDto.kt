package ru.nsu.ccfit.verba.core.model.dto.auth

import kotlinx.serialization.Serializable

@Serializable
data class SignUpDto(
    val username: String,
    val email: String,
    val password: String
)