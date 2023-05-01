package ru.nsu.ccfit.verbaapp.core.data.repo

import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi

interface CatalogRepository {
    suspend fun add(name: String, groupId: Long): ResultApi<Unit>
    suspend fun delete(id: Long): ResultApi<Unit>
    suspend fun getById(id: Long): ResultApi<CatalogDto>
    suspend fun getAllByGroup(groupId: Long): ResultApi<List<CatalogDto>>
}