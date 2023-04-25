package ru.nsu.ccfit.verbaapp.ui.screen.group

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto
import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ViewModelResult
import ru.nsu.ccfit.verbaapp.core.data.repo.CatalogRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.GroupRepository
import ru.nsu.ccfit.verbaapp.ui.screen.main.MainUiEvent
import ru.nsu.ccfit.verbaapp.ui.screen.main.MainViewModelEvent
import javax.inject.Inject


@HiltViewModel
class GroupViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val groupRepository: GroupRepository
) : ViewModel() {

    val group = mutableStateOf(GroupDto(1L, "name"))

    var listCatalog by mutableStateOf(ArrayList<CatalogDto>())

    private val resultChannel = Channel<GroupViewModelEvent>()
    val event = resultChannel.receiveAsFlow()


    fun onEvent(event: GroupUiEvent) {
        when (event) {
            is GroupUiEvent.LoadGroup -> {
                loadGroup(event.groupId)
            }

            is GroupUiEvent.CreateCatalog -> {
                createCatalog(event.name, group.value.id)
            }

            is GroupUiEvent.DeleteCatalog -> deleteCatalog(event.value.id)
            GroupUiEvent.RefreshCatalogs -> updateListCatalog()
        }
    }

    private suspend fun updateListCatalogByGroup() {
        when (val response = catalogRepository.getAllByGroup(group.value.id)) {
            is ViewModelResult.WithData -> {
                listCatalog = response.data as ArrayList<CatalogDto>
            }

            else -> {
                resultChannel.send(GroupViewModelEvent.Message("Ошибка"))
            }
        }
    }

    private fun updateListCatalog() = viewModelScope.launch {
        updateListCatalogByGroup()
    }


    private fun deleteCatalog(id: Long) = viewModelScope.launch {

        when (catalogRepository.delete(id)) {
            is ViewModelResult.WithData -> {
                resultChannel.send(GroupViewModelEvent.Message("Каталог удален"))
                //TODO: Обновление списка каталогов
                updateListCatalog()

            }

            else -> {
                resultChannel.send(GroupViewModelEvent.Message("Ошибка"))
            }
        }
    }

    private fun createCatalog(name: String, groupId: Long) = viewModelScope.launch {

        when (catalogRepository.add(name, groupId)) {
            is ViewModelResult.WithData -> {
                resultChannel.send(GroupViewModelEvent.Message("Успешное добавление $name"))
                //TODO: Обновление списка каталогов
                updateListCatalog()
            }

            else -> {
                resultChannel.send(GroupViewModelEvent.Message("Ошибка"))
            }
        }
    }

    private fun loadGroup(groupId: Long) = viewModelScope.launch {

        when (val response = groupRepository.get(groupId)) {
            is ViewModelResult.WithData -> {
                group.value = response.data ?: group.value
                //TODO: Обновление списка каталогов
                updateListCatalog()
            }

            else -> {
                resultChannel.send(GroupViewModelEvent.Message("Ошибка"))
            }
        }
    }
}