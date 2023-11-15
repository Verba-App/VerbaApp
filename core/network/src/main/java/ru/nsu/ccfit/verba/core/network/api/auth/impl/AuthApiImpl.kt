package ru.nsu.ccfit.verba.core.network.api.auth.impl

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.parameter
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.contentType
import ru.nsu.ccfit.verba.core.model.dto.Response
import ru.nsu.ccfit.verba.core.model.dto.auth.SignInDto
import ru.nsu.ccfit.verba.core.model.dto.auth.SignUpDto
import ru.nsu.ccfit.verba.core.network.api.HttpRoute
import ru.nsu.ccfit.verba.core.network.api.auth.AuthApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AuthApiImpl @Inject constructor(
    private val httpClient: HttpClient,
) : AuthApi {
    override suspend fun signIn(authDto: SignInDto): Response<String> {
        return httpClient.post(HttpRoute.signIn) {
            contentType(io.ktor.http.ContentType.Application.Json)
            setBody(authDto)
        }.body()
    }

    override suspend fun signUp(authDto: SignUpDto): Response<Unit> {
        return httpClient.post(HttpRoute.signUp) {
            contentType(io.ktor.http.ContentType.Application.Json)
            setBody(authDto)
        }.body()
    }

    override suspend fun authenticate(token: String): Response<Unit> {
        return httpClient.post(HttpRoute.authenticate) {
            parameter("token", token)
        }.body()
    }
}