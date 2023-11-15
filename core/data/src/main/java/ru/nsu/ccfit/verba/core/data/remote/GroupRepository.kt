package ru.nsu.ccfit.verba.core.data.remote

import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result

interface GroupRepository {
    suspend fun getGroupById(id: Long): Result<Group>

    suspend fun createGroup(
        name: String
    ): Result<Unit>


    suspend fun deleteGroup(
        id: Long
    ): Result<Unit>


    suspend fun getAllGroupsByUser(
    ): Result<List<Group>>

}