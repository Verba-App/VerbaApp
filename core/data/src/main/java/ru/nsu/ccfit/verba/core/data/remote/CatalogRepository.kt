package ru.nsu.ccfit.verba.core.data.remote

import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.model.Result

interface CatalogRepository {
    suspend fun createCatalog(name: String, groupId: Long): Result<Unit>
    suspend fun deleteCatalog(catalogId: Long): Result<Unit>
    suspend fun getCatalogById(catalogId: Long): Result<Catalog>
    suspend fun getAllCatalogByGroup(groupId: Long): Result<List<Catalog>>

}