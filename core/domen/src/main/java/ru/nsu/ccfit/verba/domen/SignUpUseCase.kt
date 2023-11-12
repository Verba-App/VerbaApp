package ru.nsu.ccfit.verba.domen

import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Code
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String): SignUpResult {
        val response = repository.signUp(username, email,password)
        return when (response.code) {
            Code.OK -> {
                SignUpResult.Success
            }

            else -> {
                SignUpResult.UnknownError(response.message!!)
            }
        }

    }

}

sealed class SignUpResult {
    data object Success : SignUpResult()
    data object NoCorrectData : SignUpResult()
    class UnknownError(message: String) : SignUpResult()
}
