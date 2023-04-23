package ru.nsu.ccfit.verbaapp.core.data.repo.impl

import ru.nsu.ccfit.verbaapp.api.component.Code
import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto
import ru.nsu.ccfit.verbaapp.api.data.service.GroupService
import ru.nsu.ccfit.verbaapp.core.data.domen.ViewModelResult
import ru.nsu.ccfit.verbaapp.core.data.repo.GroupRepository

class GroupRepositoryImpl(
    private val service: GroupService,
) : GroupRepository {

    override suspend fun add(name: String): ViewModelResult<Unit> {
        val response = service.createGroup(name)

        return when (response.code) {
            Code.OK -> ViewModelResult.WithData()
            else -> ViewModelResult.WithError()
        }
    }

    override suspend fun delete(id: Long): ViewModelResult<Unit> {
        val response = service.deleteGroup(id)

        return when (response.code) {
            Code.OK -> ViewModelResult.WithData()
            else -> ViewModelResult.WithError()
        }
    }

    override suspend fun getAllMyGroup(): ViewModelResult<List<GroupDto>> {
        val response = service.getAllGroupsByUser()

        return when (response.code) {
            Code.OK -> ViewModelResult.WithData(response.data)
            else -> ViewModelResult.WithError()
        }
    }

    override suspend fun getAllAvailableGroup(): ViewModelResult<List<GroupDto>> {
        val response = service.getAllAvailableGroups()

        return when (response.code) {
            Code.OK -> ViewModelResult.WithData(response.data)
            else -> ViewModelResult.WithError()
        }
    }

}