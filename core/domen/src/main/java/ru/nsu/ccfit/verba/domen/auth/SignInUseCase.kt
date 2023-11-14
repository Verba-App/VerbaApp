package ru.nsu.ccfit.verba.domen.auth

import ru.nsu.ccfit.verba.core.data.local.TokenRepository
import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        val response = repository.signIn(username, password)
        return when (response.code) {
            Code.OK -> {
                tokenRepository.setToken(response.data!!)
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