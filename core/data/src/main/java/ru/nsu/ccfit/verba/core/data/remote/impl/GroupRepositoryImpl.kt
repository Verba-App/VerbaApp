package ru.nsu.ccfit.verba.core.data.remote.impl

import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.dto.Response
import ru.nsu.ccfit.verba.core.model.dto.GroupDto
import ru.nsu.ccfit.verba.core.network.api.groups.GroupsApi
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupsApi: GroupsApi
) : GroupRepository {
    override suspend fun getGroupById(id: Long): Response<GroupDto> {
        return groupsApi.getGroupById(id)
    }

    override suspend fun createGroup(name: String): Response<Void> {
        return groupsApi.createGroup(name)
    }

    override suspend fun deleteGroup(id: Long): Response<Void> {
        return groupsApi.deleteGroup(id)
    }

    override suspend fun getAllGroupsByUser(): Response<List<GroupDto>> {
        return groupsApi.getAllGroupsByUser()
    }
}