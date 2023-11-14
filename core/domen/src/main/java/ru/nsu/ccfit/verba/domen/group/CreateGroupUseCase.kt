package ru.nsu.ccfit.verba.domen.group

import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import javax.inject.Inject

class CreateGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(name: String): Result<Unit> {
        val response = repository.createGroup(name)
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