package ru.nsu.ccfit.verbaapp.ui.screen.group


sealed class GroupViewModelEvent {
    data class Message(val value: String) : GroupViewModelEvent()
}