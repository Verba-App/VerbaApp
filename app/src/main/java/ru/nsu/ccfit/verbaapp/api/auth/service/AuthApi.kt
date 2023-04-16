package ru.nsu.ccfit.verbaapp.api.auth.service

import retrofit2.http.*
import ru.nsu.ccfit.verbaapp.api.auth.dto.AuthRequestDto
import ru.nsu.ccfit.verbaapp.api.auth.dto.RegistryDto
import ru.nsu.ccfit.verbaapp.api.component.Response

interface AuthApi {

    @POST("/auth/register")
    suspend fun signUp(
        @Body request: RegistryDto
    ) : Response<Unit>

    @POST("/auth/token")
    suspend fun signIn(
        @Body request: AuthRequestDto
    ): Response<String>

    @POST("/auth/authenticate")
    suspend fun authenticate(
        @Query("token") token: String
    ): Response<Unit>
}