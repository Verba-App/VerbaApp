package ru.nsu.ccfit.verbaapp.ui.screen.card.create

sealed class CreateCardModelEvent {
    data class Message(val value: String) : CreateCardModelEvent()
    object CloseScreen : CreateCardModelEvent()
}
