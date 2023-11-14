package ru.nsu.ccfit.verba.core.data.remote

import ru.nsu.ccfit.verba.core.model.dto.Response
import ru.nsu.ccfit.verba.core.model.dto.GroupDto

interface GroupRepository {
    suspend fun getGroupById(id: Long): Response<GroupDto>

    suspend fun createGroup(
        name: String
    ): Response<Void>


    suspend fun deleteGroup(
        id: Long
    ): Response<Void>


    suspend fun getAllGroupsByUser(
    ): Response<List<GroupDto>>

}