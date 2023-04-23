package ru.nsu.ccfit.verbaapp.api.data.service

import retrofit2.http.*
import ru.nsu.ccfit.verbaapp.api.component.Response
import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto

interface GroupService {
    @GET("api/group/{id}")
    suspend fun getGroupById(@Path("id") id: Long): Response<GroupDto>

    @POST("api/group/create")
    suspend fun createGroup(
        @Query("name") name: String
    ): Response<Void>

    @POST("api/group/{id}/delete")
    suspend fun deleteGroup(
        @Path("id") id: Long
    ): Response<Void>

    @GET("api/group/all/user")
    suspend fun getAllGroupsByUser(
    ): Response<List<GroupDto>>

    @GET("api/group/all/available")
    suspend fun getAllAvailableGroups(): Response<List<GroupDto>>
}