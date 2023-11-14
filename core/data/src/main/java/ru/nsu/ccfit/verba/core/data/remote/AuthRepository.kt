package ru.nsu.ccfit.verba.core.data.remote

import ru.nsu.ccfit.verba.core.model.dto.Response

interface AuthRepository {
    suspend fun signIn(username: String, password: String): Response<String>
    suspend fun signUp(
        username: String,
        email: String,
        password: String
    ): Response<Unit>

    suspend fun authenticate(token: String): Response<Unit>
}