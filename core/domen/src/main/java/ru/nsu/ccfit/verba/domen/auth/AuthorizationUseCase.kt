package ru.nsu.ccfit.verba.domen.auth

import ru.nsu.ccfit.verba.core.data.local.TokenRepository
import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import javax.inject.Inject

class AuthorizationUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        val token  = tokenRepository.getToken()
        val response = repository.authenticate(token)
        return when (response.code) {
            Code.OK -> {
                Result.Success(Unit)
            }

            Code.UNAUTHORIZED -> {
                Result.Error("UNAUTHORIZED")
            }

            else -> {
                Result.Error(response.message!!)
            }
        }

    }
}