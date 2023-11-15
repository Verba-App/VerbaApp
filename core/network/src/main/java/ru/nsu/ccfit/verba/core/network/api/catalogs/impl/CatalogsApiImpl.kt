package ru.nsu.ccfit.verba.core.network.api.catalogs.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import ru.nsu.ccfit.verba.core.model.dto.CatalogDto
import ru.nsu.ccfit.verba.core.model.dto.Response
import ru.nsu.ccfit.verba.core.network.api.HttpRoute
import ru.nsu.ccfit.verba.core.network.api.catalogs.CatalogsApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogsApiImpl @Inject constructor(
    private val httpClient: HttpClient,
) : CatalogsApi {
    override suspend fun createCatalog(name: String, groupId: Long): Response<Unit> {
        return httpClient.post(HttpRoute.createCatalog) {
            parameter("name", name)
            parameter("group-id", groupId)
        }.body()
    }

    override suspend fun deleteCatalog(catalogId: Long): Response<Unit> {
        return httpClient.post(HttpRoute.deleteCatalog(catalogId)) {
        }.body()
    }

    override suspend fun getCatalogById(catalogId: Long): Response<CatalogDto> {
        return httpClient.post(HttpRoute.getCatalog(catalogId)) {
        }.body()
    }

    override suspend fun getAllCatalogByGroup(groupId: Long): Response<List<CatalogDto>> {
        return httpClient.post(HttpRoute.getAllCatalogByGroup(groupId)) {
        }.body()
    }
}