package ru.nsu.ccfit.verbaapp.ui.screen.main

import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto


sealed class MainModelEvent {
    data class Message(val value: String) : MainModelEvent()
    data class OpenGroup(val value: GroupDto) : MainModelEvent()
}
