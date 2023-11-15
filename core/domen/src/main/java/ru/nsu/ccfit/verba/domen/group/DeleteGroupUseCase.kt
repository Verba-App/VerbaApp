package ru.nsu.ccfit.verba.domen.group

import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class DeleteGroupUseCase @Inject constructor(private val repository: GroupRepository) {
    suspend operator fun invoke(group: Group): Result<Unit> {
        return repository.deleteGroup(group.id)
    }

}