package ru.nsu.ccfit.verba.domen.auth

import ru.nsu.ccfit.verba.core.data.local.TokenRepository
import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class AuthorizationUseCase @Inject constructor(
    private val repository: AuthRepository,
    private val tokenRepository: TokenRepository
) {
    suspend operator fun invoke(): Result<Unit> {
        val token = tokenRepository.getToken()
        return repository.authenticate(token)
    }

}