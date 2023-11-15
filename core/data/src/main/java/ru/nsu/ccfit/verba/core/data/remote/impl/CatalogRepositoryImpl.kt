package ru.nsu.ccfit.verba.core.data.remote.impl

import ru.nsu.ccfit.verba.core.data.model.toListCatalogResult
import ru.nsu.ccfit.verba.core.data.model.toResultCatalog
import ru.nsu.ccfit.verba.core.data.model.toUnitResult
import ru.nsu.ccfit.verba.core.data.remote.CatalogRepository
import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.network.api.catalogs.CatalogsApi
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val catalogsApi: CatalogsApi
) : CatalogRepository  {
    override suspend fun createCatalog(name: String, groupId: Long): Result<Unit> {
        return catalogsApi.createCatalog(name, groupId).toUnitResult()
    }

    override suspend fun deleteCatalog(catalogId: Long): Result<Unit> {
        return catalogsApi.deleteCatalog(catalogId).toUnitResult()
    }

    override suspend fun getCatalogById(catalogId: Long): Result<Catalog> {
        return catalogsApi.getCatalogById(catalogId).toResultCatalog()
    }

    override suspend fun getAllCatalogByGroup(groupId: Long): Result<List<Catalog>> {
        return catalogsApi.getAllCatalogByGroup(groupId).toListCatalogResult()
    }
}