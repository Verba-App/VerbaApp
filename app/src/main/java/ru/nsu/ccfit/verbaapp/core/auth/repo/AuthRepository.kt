package ru.nsu.ccfit.verbaapp.core.auth.repo

import ru.nsu.ccfit.verbaapp.core.auth.domen.AuthResult

interface AuthRepository {
    suspend fun signUp(username: String, email: String, password: String): AuthResult<Unit>
    suspend fun signIn(username: String, password: String): AuthResult<Unit>
    suspend fun authenticate(): AuthResult<Unit>
}