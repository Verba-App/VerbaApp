package ru.nsu.ccfit.verbaapp.ui.screen.catalog

sealed class CatalogModelEvent {
    data class Message(val value: String) : CatalogModelEvent()
}
