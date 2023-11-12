package ru.nsu.ccfit.verba.domen

import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Code
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): SignInResult {
        val response = repository.signIn(username, password)
        return when (response.code) {
            Code.OK -> {
                SignInResult.Authorized
            }

            Code.UNAUTHORIZED -> {
                SignInResult.Unauthorized
            }

            else -> {
                SignInResult.UnknownError(response.message!!)
            }
        }

    }
}

sealed class SignInResult {
    data object Authorized : SignInResult()
    data object Unauthorized : SignInResult()
    class UnknownError(message: String) : SignInResult()
}