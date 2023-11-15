package ru.nsu.ccfit.verba.core.data.remote.impl

import ru.nsu.ccfit.verba.core.data.remote.CatalogRepository
import ru.nsu.ccfit.verba.core.model.dto.CatalogDto
import ru.nsu.ccfit.verba.core.model.dto.Response
import ru.nsu.ccfit.verba.core.network.api.catalogs.CatalogsApi
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val catalogsApi: CatalogsApi
) : CatalogRepository  {
    override suspend fun createCatalog(name: String, groupId: Long): Response<Unit> {
        return catalogsApi.createCatalog(name, groupId)
    }

    override suspend fun deleteCatalog(catalogId: Long): Response<Unit> {
        return catalogsApi.deleteCatalog(catalogId)
    }

    override suspend fun getCatalogById(catalogId: Long): Response<CatalogDto> {
        return catalogsApi.getCatalogById(catalogId)
    }

    override suspend fun getAllCatalogByGroup(groupId: Long): Response<List<CatalogDto>> {
        return catalogsApi.getAllCatalogByGroup(groupId)
    }
}