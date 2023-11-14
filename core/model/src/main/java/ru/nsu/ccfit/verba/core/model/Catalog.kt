package ru.nsu.ccfit.verba.core.model

import ru.nsu.ccfit.verba.core.model.dto.CatalogDto

data class Catalog(val id: Long, val name: String)

fun CatalogDto.toModel(): Catalog {
    return Catalog(id, name)
}