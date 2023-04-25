package ru.nsu.ccfit.verbaapp.ui.screen.main

import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto

sealed class MainUiEvent {
    data class CreateMain(val name: String) : MainUiEvent()
    data class DeleteMain(val value: GroupDto) : MainUiEvent()
    object RefreshMain : MainUiEvent()
    data class AvalableMain(val filter: Boolean) : MainUiEvent()
    data class ViewMain(val value: GroupDto) : MainUiEvent()
}
