package ru.nsu.ccfit.verba.domen.auth

import ru.nsu.ccfit.verba.core.data.local.TokenRepository
import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class SignInUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        return when (val response = repository.signIn(username, password)) {
            is Result.Error -> {
                Result.Error(response.message)
            }
            is Result.Success -> {
                tokenRepository.setToken(response.data)
                Result.Success(Unit)
            }
        }

    }
}