package ru.nsu.ccfit.verba.core.data.remote

import ru.nsu.ccfit.verba.core.model.Result


interface AuthRepository {
    suspend fun signIn(username: String, password: String): Result <String>
    suspend fun signUp(
        username: String,
        email: String,
        password: String
    ): Result <Unit>

    suspend fun authenticate(token: String): Result <Unit>
}