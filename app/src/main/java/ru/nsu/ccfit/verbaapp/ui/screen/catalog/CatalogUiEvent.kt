package ru.nsu.ccfit.verbaapp.ui.screen.catalog

sealed class CatalogUiEvent {
    data class LoadCatalog(val catalogId: Long) : CatalogUiEvent()
    data class CreateCard(val catalogId: Long) : CatalogUiEvent()
    object StudyCatalog : CatalogUiEvent()
}
