package ru.nsu.ccfit.verbaapp.ui.screen.group

import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto

sealed class GroupUiEvent {
    data class LoadGroup(val groupId: Long) : GroupUiEvent()
    data class CreateCatalog(val name: String) : GroupUiEvent()
    data class DeleteCatalog(val value: CatalogDto) : GroupUiEvent()
    data class OpenCatalog(val value: CatalogDto) : GroupUiEvent()
    object RefreshCatalogs : GroupUiEvent()
}
