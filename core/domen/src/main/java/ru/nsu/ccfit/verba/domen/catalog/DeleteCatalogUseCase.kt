package ru.nsu.ccfit.verba.domen.catalog

import ru.nsu.ccfit.verba.core.data.remote.CatalogRepository
import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.model.Result
import javax.inject.Inject

class DeleteCatalogUseCase  @Inject constructor(private val repository: CatalogRepository) {
    suspend operator fun invoke(catalog: Catalog): Result<Unit> {
        return repository.deleteCatalog(catalog.id)
    }
}