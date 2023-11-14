package ru.nsu.ccfit.verba.core.network.api.auth


import ru.nsu.ccfit.verba.core.model.dto.Response
import ru.nsu.ccfit.verba.core.model.dto.auth.SignInDto
import ru.nsu.ccfit.verba.core.model.dto.auth.SignUpDto

interface AuthApi {
    suspend fun signIn(authDto: SignInDto): Response<String>
    suspend fun signUp(authDto: SignUpDto): Response<Unit>
    suspend fun authenticate(token:String): Response<Unit>

}