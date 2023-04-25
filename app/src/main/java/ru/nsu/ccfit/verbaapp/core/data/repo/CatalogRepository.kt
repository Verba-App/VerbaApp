package ru.nsu.ccfit.verbaapp.core.data.repo

import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ViewModelResult

interface CatalogRepository {
    suspend fun add(name: String, groupId: Long): ViewModelResult<Unit>
    suspend fun delete(id: Long): ViewModelResult<Unit>
    suspend fun getById(id: Long): ViewModelResult<CatalogDto>
    suspend fun getAllByGroup(groupId: Long): ViewModelResult<List<CatalogDto>>
}