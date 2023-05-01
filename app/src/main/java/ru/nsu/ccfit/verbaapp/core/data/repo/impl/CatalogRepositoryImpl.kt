package ru.nsu.ccfit.verbaapp.core.data.repo.impl

import ru.nsu.ccfit.verbaapp.api.component.Code
import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto
import ru.nsu.ccfit.verbaapp.api.data.service.CatalogService
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi
import ru.nsu.ccfit.verbaapp.core.data.repo.CatalogRepository

class CatalogRepositoryImpl(
    private val service: CatalogService
) : CatalogRepository {
    override suspend fun add(name: String, groupId: Long): ResultApi<Unit> {
        val response = service.createCatalog(name, groupId)

        return when (response.code) {
            Code.OK -> ResultApi.WithData()
            else -> ResultApi.WithError()
        }
    }

    override suspend fun delete(id: Long): ResultApi<Unit> {
        val response = service.deleteCatalog(id)
        return when (response.code) {
            Code.OK -> ResultApi.WithData()
            else -> ResultApi.WithError()
        }
    }

    override suspend fun getById(id: Long): ResultApi<CatalogDto> {
        val response = service.getCatalog(id)

        return when (response.code) {
            Code.OK -> ResultApi.WithData(response.data)
            else -> ResultApi.WithError()
        }
    }

    override suspend fun getAllByGroup(groupId: Long): ResultApi<List<CatalogDto>> {
        val response = service.getAllCatalogByGroup(groupId)

        return when (response.code) {
            Code.OK -> ResultApi.WithData(response.data)
            else -> ResultApi.WithError()
        }
    }
}