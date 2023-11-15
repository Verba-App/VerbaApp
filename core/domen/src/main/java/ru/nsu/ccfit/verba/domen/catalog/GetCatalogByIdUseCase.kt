package ru.nsu.ccfit.verba.domen.catalog

import ru.nsu.ccfit.verba.core.data.remote.CatalogRepository
import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class GetCatalogByIdUseCase  @Inject constructor(private val repository: CatalogRepository) {
    suspend operator fun invoke(group: Group): Result<Catalog> {
      return repository.getCatalogById(group.id)
    }
}