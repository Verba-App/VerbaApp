package ru.nsu.ccfit.verba.core.network.api.groups

import ru.nsu.ccfit.verba.core.model.Response
import ru.nsu.ccfit.verba.core.model.dto.GroupDto

interface GroupsApi {

    suspend fun getGroupById(id: Long): Response<GroupDto>


    suspend fun createGroup(
        name: String
    ): Response<Void>


    suspend fun deleteGroup(
     id: Long
    ): Response<Void>


    suspend fun getAllGroupsByUser(
    ): Response<List<GroupDto>>

    suspend fun getAllAvailableGroups(): Response<List<GroupDto>>
}