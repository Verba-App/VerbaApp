package ru.nsu.ccfit.verba.feature.detailsgroup

import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.model.Group

sealed class GroupDetailUiEvent {
    data class CreateCatalog(val name: String) : GroupDetailUiEvent()
    data object UpdateListCatalogs : GroupDetailUiEvent()
    data class DeleteCatalog(val catalog: Catalog) : GroupDetailUiEvent()
    // data class ChooseGroup(val value: Group) : GroupsUiEvent()
}

data class DetailGroupState(
    val group: Group,
    var catalogs: List<Catalog>
)

sealed interface DetailGroupUiState {
    data object SuccessDeleteCatalog : DetailGroupUiState
    data object SuccessCreateCatalog : DetailGroupUiState
    class Error(val message: String) : DetailGroupUiState
}