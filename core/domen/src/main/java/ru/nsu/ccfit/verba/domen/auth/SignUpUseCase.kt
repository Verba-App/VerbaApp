package ru.nsu.ccfit.verba.domen.auth

import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class SignUpUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(username: String, email: String, password: String): Result<Unit> {
        return repository.signUp(username, email, password)
    }

}

