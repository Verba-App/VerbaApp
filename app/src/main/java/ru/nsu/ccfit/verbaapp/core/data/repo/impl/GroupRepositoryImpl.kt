package ru.nsu.ccfit.verbaapp.core.data.repo.impl

import ru.nsu.ccfit.verbaapp.api.component.Code
import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto
import ru.nsu.ccfit.verbaapp.api.data.service.GroupService
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi
import ru.nsu.ccfit.verbaapp.core.data.repo.GroupRepository

class GroupRepositoryImpl(
    private val service: GroupService,
) : GroupRepository {

    override suspend fun add(name: String): ResultApi<Unit> {
        val response = service.createGroup(name)

        return when (response.code) {
            Code.OK -> ResultApi.WithData()
            else -> ResultApi.WithError()
        }
    }

    override suspend fun delete(id: Long): ResultApi<Unit> {
        val response = service.deleteGroup(id)

        return when (response.code) {
            Code.OK -> ResultApi.WithData()
            else -> ResultApi.WithError()
        }
    }

    override suspend fun getAllMyGroup(): ResultApi<List<GroupDto>> {
        val response = service.getAllGroupsByUser()

        return when (response.code) {
            Code.OK -> ResultApi.WithData(response.data)
            else -> ResultApi.WithError()
        }
    }

    override suspend fun getAllAvailableGroup(): ResultApi<List<GroupDto>> {
        val response = service.getAllAvailableGroups()

        return when (response.code) {
            Code.OK -> ResultApi.WithData(response.data)
            else -> ResultApi.WithError()
        }
    }

    override suspend fun get(groupId: Long): ResultApi<GroupDto> {
        val response = service.getGroupById(groupId)

        return when (response.code) {
            Code.OK -> ResultApi.WithData(response.data)
            else -> ResultApi.WithError()
        }
    }

}