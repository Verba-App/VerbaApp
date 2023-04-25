package ru.nsu.ccfit.verbaapp.ui.screen.main

import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto


sealed class MainViewModelEvent {
    data class Message(val value: String) : MainViewModelEvent()
    data class OpenGroup(val value: GroupDto) : MainViewModelEvent()
}
