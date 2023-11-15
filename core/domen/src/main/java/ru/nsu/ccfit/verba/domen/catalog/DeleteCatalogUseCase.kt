package ru.nsu.ccfit.verba.domen.catalog

import ru.nsu.ccfit.verba.core.data.remote.CatalogRepository
import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.model.dto.Code
import javax.inject.Inject

class DeleteCatalogUseCase  @Inject constructor(private val repository: CatalogRepository) {
    suspend operator fun invoke(catalog: Catalog): Result<Unit> {
        val response = repository.deleteCatalog(catalog.id)
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