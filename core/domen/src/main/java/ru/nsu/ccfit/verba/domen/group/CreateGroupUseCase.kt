package ru.nsu.ccfit.verba.domen.group

import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class CreateGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(name: String): Result<Unit> {
        return repository.createGroup(name)
    }

}