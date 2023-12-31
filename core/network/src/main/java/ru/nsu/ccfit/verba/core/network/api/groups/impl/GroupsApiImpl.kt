package ru.nsu.ccfit.verba.core.network.api.groups.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import ru.nsu.ccfit.verba.core.network.dto.Response
import ru.nsu.ccfit.verba.core.network.api.HttpRoute
import ru.nsu.ccfit.verba.core.network.api.groups.GroupsApi
import ru.nsu.ccfit.verba.core.network.dto.GroupDto
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GroupsApiImpl @Inject constructor(
private val httpClient: HttpClient,
) : GroupsApi {
    override suspend fun getGroupById(id: Long): Response<GroupDto> {
        return httpClient.get(HttpRoute.getGroupById(id)) {
        }.body()
    }

    override suspend fun createGroup(name: String): Response<Unit> {
        return httpClient.post(HttpRoute.createGroup) {
            parameter("name",name)
        }.body()
    }

    override suspend fun deleteGroup(id: Long): Response<Unit> {
        return httpClient.post(HttpRoute.deleteGroup(id)) {
        }.body()
    }

    override suspend fun getAllGroupsByUser(): Response<List<GroupDto>> {
        return httpClient.get(HttpRoute.getAllGroupsByUser) {
        }.body()
    }

    override suspend fun getAllAvailableGroups(): Response<List<GroupDto>> {
        return httpClient.get(HttpRoute.getAllAvailableGroups) {
        }.body()
    }
}