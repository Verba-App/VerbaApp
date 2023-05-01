package ru.nsu.ccfit.verbaapp.core.data.repo

import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi

interface GroupRepository {
   suspend fun add(name: String): ResultApi<Unit>
   suspend fun delete(id: Long): ResultApi<Unit>
   suspend fun getAllMyGroup(): ResultApi<List<GroupDto>>
   suspend fun getAllAvailableGroup(): ResultApi<List<GroupDto>>
   suspend fun get(groupId: Long): ResultApi<GroupDto>
}