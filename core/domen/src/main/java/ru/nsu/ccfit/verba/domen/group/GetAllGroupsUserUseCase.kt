package ru.nsu.ccfit.verba.domen.group

import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class GetAllGroupsUserUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(): Result<List<Group>> {
        return repository.getAllGroupsByUser()
    }

}