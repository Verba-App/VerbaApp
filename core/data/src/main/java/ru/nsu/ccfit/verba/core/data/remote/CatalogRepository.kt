package ru.nsu.ccfit.verba.core.data.remote

import ru.nsu.ccfit.verba.core.model.dto.CatalogDto
import ru.nsu.ccfit.verba.core.model.dto.Response

interface CatalogRepository {
    suspend fun createCatalog(name: String, groupId: Long) : Response<Unit>
    suspend fun deleteCatalog(catalogId:Long): Response<Unit>
    suspend fun getCatalogById(catalogId: Long): Response<CatalogDto>
    suspend fun getAllCatalogByGroup(groupId: Long) : Response<List<CatalogDto>>

}