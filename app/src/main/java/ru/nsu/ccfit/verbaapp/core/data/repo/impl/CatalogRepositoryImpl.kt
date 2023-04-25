package ru.nsu.ccfit.verbaapp.core.data.repo.impl

import ru.nsu.ccfit.verbaapp.api.component.Code
import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto
import ru.nsu.ccfit.verbaapp.api.data.service.CatalogService
import ru.nsu.ccfit.verbaapp.core.data.domen.ViewModelResult
import ru.nsu.ccfit.verbaapp.core.data.repo.CatalogRepository

class CatalogRepositoryImpl(
    private val service: CatalogService
) : CatalogRepository {
    override suspend fun add(name: String, groupId: Long): ViewModelResult<Unit> {
        val response = service.createCatalog(name, groupId)

        return when (response.code) {
            Code.OK -> ViewModelResult.WithData()
            else -> ViewModelResult.WithError()
        }
    }

    override suspend fun delete(id: Long): ViewModelResult<Unit> {
        val response = service.deleteCatalog(id)
        return when (response.code) {
            Code.OK -> ViewModelResult.WithData()
            else -> ViewModelResult.WithError()
        }
    }

    override suspend fun getById(id: Long): ViewModelResult<CatalogDto> {
        val response = service.getCatalog(id)

        return when (response.code) {
            Code.OK -> ViewModelResult.WithData(response.data)
            else -> ViewModelResult.WithError()
        }
    }

    override suspend fun getAllByGroup(groupId: Long): ViewModelResult<List<CatalogDto>> {
        val response = service.getAllCatalogByGroup(groupId)

        return when (response.code) {
            Code.OK -> ViewModelResult.WithData(response.data)
            else -> ViewModelResult.WithError()
        }
    }
}