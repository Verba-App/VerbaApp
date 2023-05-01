package ru.nsu.ccfit.verbaapp.ui.screen.group

import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto


sealed class GroupModelEvent {
    data class Message(val value: String) : GroupModelEvent()
    data class OpenCatalog(val value: CatalogDto) : GroupModelEvent()
}