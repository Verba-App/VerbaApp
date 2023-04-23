package ru.nsu.ccfit.verbaapp.ui.screen.main

import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto

sealed class GroupUiEvent {
    data class CreateGroup(val name: String) : GroupUiEvent()
    data class DeleteGroup(val value: GroupDto) : GroupUiEvent()
    data class ViewGroup(val value: GroupDto) : GroupUiEvent()
}
