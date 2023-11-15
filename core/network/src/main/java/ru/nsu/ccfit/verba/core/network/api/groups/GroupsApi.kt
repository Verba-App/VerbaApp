package ru.nsu.ccfit.verba.core.network.api.groups

import ru.nsu.ccfit.verba.core.network.dto.GroupDto
import ru.nsu.ccfit.verba.core.network.dto.Response

interface GroupsApi {

    suspend fun getGroupById(id: Long): Response<GroupDto>


    suspend fun createGroup(
        name: String
    ): Response<Unit>


    suspend fun deleteGroup(
     id: Long
    ): Response<Unit>


    suspend fun getAllGroupsByUser(
    ): Response<List<GroupDto>>

    suspend fun getAllAvailableGroups(): Response<List<GroupDto>>
}