package ru.nsu.ccfit.verba.domen.auth

import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String): Result<Unit> {
        val response = repository.signUp(username, email,password)
        return when (response.code) {
            Code.OK -> {
                Result.Success(Unit)
            }

            else -> {
                Result.Error(response.message!!)
            }
        }

    }

}

