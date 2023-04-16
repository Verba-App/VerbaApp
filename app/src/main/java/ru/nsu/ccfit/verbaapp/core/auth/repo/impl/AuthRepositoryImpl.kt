package ru.nsu.ccfit.verbaapp.core.auth.repo.impl

import android.content.SharedPreferences
import ru.nsu.ccfit.verbaapp.api.auth.dto.AuthRequestDto
import ru.nsu.ccfit.verbaapp.api.auth.dto.RegistryDto
import ru.nsu.ccfit.verbaapp.api.auth.service.AuthApi
import ru.nsu.ccfit.verbaapp.core.auth.domen.AuthResult
import ru.nsu.ccfit.verbaapp.core.auth.repo.AuthRepository
import ru.nsu.ccfit.verbaapp.api.component.Code

class AuthRepositoryImpl(
    private val api: AuthApi,
    private val prefs: SharedPreferences
) : AuthRepository {

    override suspend fun signUp(
        username: String,
        email: String,
        password: String
    ): AuthResult<Unit> {

        val response = api.signUp(
            request = RegistryDto(
                username = username,
                email = email,
                password = password
            )
        )
        return when (response.code) {
            Code.OK -> signIn(username, password)
            Code.UNAUTHORIZED -> AuthResult.Unauthorized()
            else -> AuthResult.UnknownError()
        }
    }

    override suspend fun signIn(username: String, password: String): AuthResult<Unit> {
        val response = api.signIn(
            request = AuthRequestDto(
                username = username,
                password = password
            )
        )

        return when (response.code) {
            Code.OK -> {
                prefs.edit()
                    .putString("jwt", response.data)
                    .apply()
                AuthResult.Authorized()
            }
            Code.UNAUTHORIZED -> AuthResult.Unauthorized()
            else ->
                AuthResult.UnknownError()
        }
    }

    override suspend fun authenticate(): AuthResult<Unit> {

        val token = prefs.getString("jwt", null) ?: return AuthResult.Unauthorized()
        val response = api.authenticate(token)

        return when (response.code) {
            Code.OK -> AuthResult.Authorized()
            Code.UNAUTHORIZED -> AuthResult.Unauthorized()
            else -> AuthResult.UnknownError()

        }
    }
}