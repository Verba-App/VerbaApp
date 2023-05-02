package ru.nsu.ccfit.verbaapp.ui.screen.card.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verbaapp.api.data.dto.ImageDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi
import ru.nsu.ccfit.verbaapp.core.data.repo.CardRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.DataRepository
import javax.inject.Inject

@HiltViewModel
class CreateCardViewModel @Inject constructor(
    private val dataRepository: DataRepository,
    private val cardRepository: CardRepository
) : ViewModel() {

    var word by mutableStateOf("")
    var transcription by mutableStateOf("")
    var image by mutableStateOf(ImageDto(""))

    var listImage by mutableStateOf(listOf<ImageDto>())


    private val resultChannel = Channel<CreateCardModelEvent>()
    val event = resultChannel.receiveAsFlow()

    fun onEvent(event: CreateCardUiEvent) {
        when (event) {
            is CreateCardUiEvent.SearchWord -> {
                searchImage(event.word)
            }

            CreateCardUiEvent.Cancel -> {
                closeScreen()
            }

            is CreateCardUiEvent.CreateCard -> TODO()
        }
    }

    private fun closeScreen() = viewModelScope.launch {
        resultChannel.send(CreateCardModelEvent.CloseScreen)
    }

    private fun searchImage(text: String) = viewModelScope.launch {
        when (val response = dataRepository.searchImage(text)) {
            is ResultApi.WithData -> {
                listImage = (response.data ?: listImage)
            }
            else -> {
                resultChannel.send(CreateCardModelEvent.Message("Ошибка"))
            }
        }
    }
}