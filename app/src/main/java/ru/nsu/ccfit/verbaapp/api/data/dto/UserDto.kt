package ru.nsu.ccfit.verbaapp.api.data.dto

data class UserDto(
    val id: Long,
    val name: String,
    val email: String,
    val region: String,
    val password: String
)