package ru.nsu.ccfit.verbaapp.ui.screen.catalog

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verbaapp.api.data.dto.CardDto
import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi
import ru.nsu.ccfit.verbaapp.core.data.repo.CardRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.CatalogRepository
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val cardRepository: CardRepository
) : ViewModel() {

    val catalog = mutableStateOf(CatalogDto(1L, "name"))
    var cards by mutableStateOf(ArrayList<CardDto>())

    private val resultChannel = Channel<CatalogModelEvent>()
    val event = resultChannel.receiveAsFlow()

    fun onEvent(event: CatalogUiEvent) {
        when (event) {
            is CatalogUiEvent.LoadCatalog -> {
                loadCatalog(event.catalogId)
                loadCardByCatalog(event.catalogId)
            }
        }
    }

    private fun loadCatalog(catalogId: Long) = viewModelScope.launch {
        when (val response = catalogRepository.getById(catalogId)) {
            is ResultApi.WithData -> {
                catalog.value = response.data ?: catalog.value
            }
            else -> {
                resultChannel.send(CatalogModelEvent.Message("Ошибка"))
            }
        }
    }

    private fun loadCardByCatalog(catalogId: Long) = viewModelScope.launch {
        when (val response = cardRepository.getAllCardByCatalog(catalogId)) {
            is ResultApi.WithData -> {
               cards = response.data as ArrayList<CardDto>
            }
            else -> {
                resultChannel.send(CatalogModelEvent.Message("Ошибка"))
            }
        }
    }


}