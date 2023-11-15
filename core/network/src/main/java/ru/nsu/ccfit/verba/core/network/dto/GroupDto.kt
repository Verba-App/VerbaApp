package ru.nsu.ccfit.verba.core.network.dto

import kotlinx.serialization.Serializable

@Serializable
data class GroupDto(val id: Long, val name: String)