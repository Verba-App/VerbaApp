package ru.nsu.ccfit.verba.domen.group

import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import ru.nsu.ccfit.verba.core.model.toModel
import javax.inject.Inject

class GetAllGroupsUserUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(): Result<List<Group>> {
        val response = repository.getAllGroupsByUser()
        return when (response.code) {
            Code.OK -> {
                Result.Success(response.data!!.map { it.toModel() })
            }

            else -> {
               Result.Error(response.message!!)
            }
        }

    }

}