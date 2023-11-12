package ru.nsu.ccfit.verba.core.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val region: String,
    val password: String
)