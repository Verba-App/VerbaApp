package ru.nsu.ccfit.verbaapp.ui.screen.main

import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto


sealed class GroupViewModelEvent {
    data class Message(val value: String) : GroupViewModelEvent()
    data class OpenGroup(val value: GroupDto) : GroupViewModelEvent()
}
