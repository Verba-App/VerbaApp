package ru.nsu.ccfit.verba.domen.catalog

import ru.nsu.ccfit.verba.core.data.remote.CatalogRepository
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import javax.inject.Inject

class CreateCatalogUseCase @Inject constructor(private val repository: CatalogRepository) {
    suspend operator fun invoke(name: String, group: Group): Result<Unit> {
        val response = repository.createCatalog(name, group.id)
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