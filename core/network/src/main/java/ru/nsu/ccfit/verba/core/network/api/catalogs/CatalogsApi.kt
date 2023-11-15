package ru.nsu.ccfit.verba.core.network.api.catalogs

import ru.nsu.ccfit.verba.core.network.dto.CatalogDto
import ru.nsu.ccfit.verba.core.network.dto.Response

interface CatalogsApi {
    suspend fun createCatalog(name: String, groupId: Long) : Response<Unit>
    suspend fun deleteCatalog(catalogId:Long): Response<Unit>
    suspend fun getCatalogById(catalogId: Long) : Response<CatalogDto>
    suspend fun getAllCatalogByGroup(groupId: Long) : Response<List<CatalogDto>>

}