package ru.nsu.ccfit.verbaapp.ui.screen.catalog

sealed class CatalogModelEvent {
    data class Message(val value: String) : CatalogModelEvent()
    data class OpenCreateCardView(val catalogId: Long) : CatalogModelEvent()
}
