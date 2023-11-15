package ru.nsu.ccfit.verba.core.data.remote.impl

import ru.nsu.ccfit.verba.core.data.model.toListGroupResult
import ru.nsu.ccfit.verba.core.data.model.toResultGroup
import ru.nsu.ccfit.verba.core.data.model.toUnitResult
import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.network.api.groups.GroupsApi
import javax.inject.Inject

class GroupRepositoryImpl @Inject constructor(
    private val groupsApi: GroupsApi
) : GroupRepository {
    override suspend fun getGroupById(id: Long): Result<Group> {
        return groupsApi.getGroupById(id).toResultGroup()
    }

    override suspend fun createGroup(name: String): Result<Unit> {
        return groupsApi.createGroup(name).toUnitResult()
    }

    override suspend fun deleteGroup(id: Long): Result<Unit> {
        return groupsApi.deleteGroup(id).toUnitResult()
    }

    override suspend fun getAllGroupsByUser(): Result<List<Group>> {
        return groupsApi.getAllGroupsByUser().toListGroupResult()
    }
}