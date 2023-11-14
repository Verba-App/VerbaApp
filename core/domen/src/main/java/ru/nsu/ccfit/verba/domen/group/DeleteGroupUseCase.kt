package ru.nsu.ccfit.verba.domen.group

import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import javax.inject.Inject

class DeleteGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(group: Group): Result<Unit> {
        val response = repository.deleteGroup(group.id)
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