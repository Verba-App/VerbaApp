package ru.nsu.ccfit.verba.core.data.remote.impl

import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.dto.Response
import ru.nsu.ccfit.verba.core.model.dto.auth.SignInDto
import ru.nsu.ccfit.verba.core.model.dto.auth.SignUpDto
import ru.nsu.ccfit.verba.core.network.api.auth.AuthApi
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun signIn(username: String, password: String): Response<String> {
       return authApi.signIn(SignInDto(username, password))
    }

    override suspend fun signUp(username: String, email: String, password: String): Response<Unit> {
        return authApi.signUp(SignUpDto(username,email, password))
    }

    override suspend fun authenticate(token: String): Response<Unit> {
        return authApi.authenticate(token)
    }
}