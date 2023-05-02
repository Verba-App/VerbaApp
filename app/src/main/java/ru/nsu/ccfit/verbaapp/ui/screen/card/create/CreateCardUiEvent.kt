package ru.nsu.ccfit.verbaapp.ui.screen.card.create

sealed class CreateCardUiEvent {
    data class SearchWord(val word: String) : CreateCardUiEvent()
    object Cancel : CreateCardUiEvent()
    data class CreateCard(val catalogId:Long) : CreateCardUiEvent()
}
