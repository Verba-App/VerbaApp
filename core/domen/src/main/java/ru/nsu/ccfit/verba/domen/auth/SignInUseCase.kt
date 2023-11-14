package ru.nsu.ccfit.verba.domen.auth

import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, password: String): Result<Unit> {
        val response = repository.signIn(username, password)
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