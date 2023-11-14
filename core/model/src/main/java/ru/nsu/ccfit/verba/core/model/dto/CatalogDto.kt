package ru.nsu.ccfit.verba.core.model.dto

import kotlinx.serialization.Serializable

@Serializable
data class CatalogDto(val id: Long, val name: String)