package ru.nsu.ccfit.verba.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val region: String,
    val password: String
)