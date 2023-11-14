package ru.nsu.ccfit.verba.domen.group

import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import ru.nsu.ccfit.verba.core.model.toModel
import javax.inject.Inject

class GetGroupByIdUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(id:Long): Result<Group> {
        val response = repository.getGroupById(id)
        return when (response.code) {
            Code.OK -> {
                Result.Success(response.data!!.toModel())
            }

            else -> {
                Result.Error(response.message!!)
            }
        }

    }

}