package ru.nsu.ccfit.verbaapp.core.data.repo

import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ViewModelResult

interface GroupRepository {
   suspend fun add(name: String): ViewModelResult<Unit>
   suspend fun delete(id: Long): ViewModelResult<Unit>
   suspend fun getAllMyGroup(): ViewModelResult<List<GroupDto>>
   suspend fun getAllAvailableGroup(): ViewModelResult<List<GroupDto>>
   suspend fun get(groupId: Long): ViewModelResult<GroupDto>
}