package ru.nsu.ccfit.verba.core.data.remote.impl

import ru.nsu.ccfit.verba.core.data.model.toResultString
import ru.nsu.ccfit.verba.core.data.model.toUnitResult
import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.network.api.auth.AuthApi
import ru.nsu.ccfit.verba.core.network.dto.auth.SignInDto
import ru.nsu.ccfit.verba.core.network.dto.auth.SignUpDto
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val authApi: AuthApi
) : AuthRepository {
    override suspend fun signIn(username: String, password: String): Result<String> {
       return authApi.signIn(SignInDto(username, password)).toResultString()
    }

    override suspend fun signUp(username: String, email: String, password: String): Result<Unit> {
        return authApi.signUp(SignUpDto(username,email, password)).toUnitResult()
    }

    override suspend fun authenticate(token: String): Result<Unit> {
        return authApi.authenticate(token).toUnitResult()
    }
}