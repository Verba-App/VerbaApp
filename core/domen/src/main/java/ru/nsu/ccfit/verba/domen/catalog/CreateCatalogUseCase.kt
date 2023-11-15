package ru.nsu.ccfit.verba.domen.catalog

import ru.nsu.ccfit.verba.core.data.remote.CatalogRepository
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class CreateCatalogUseCase @Inject constructor(private val repository: CatalogRepository) {
    suspend operator fun invoke(name: String, group: Group): Result<Unit> {
        return repository.createCatalog(name, group.id)
    }
}