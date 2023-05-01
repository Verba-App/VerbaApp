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
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi
import ru.nsu.ccfit.verbaapp.core.data.repo.CatalogRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.GroupRepository
import javax.inject.Inject


@HiltViewModel
class GroupViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository,
    private val groupRepository: GroupRepository
) : ViewModel() {

    val group = mutableStateOf(GroupDto(1L, "name"))

    var listCatalog by mutableStateOf(ArrayList<CatalogDto>())

    private val resultChannel = Channel<GroupModelEvent>()
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
            is GroupUiEvent.OpenCatalog -> {
              openCatalog(event.value)
            }
        }
    }

    private fun openCatalog(catalog: CatalogDto) = viewModelScope.launch {
        resultChannel.send(GroupModelEvent.OpenCatalog(catalog))
    }

    private suspend fun updateListCatalogByGroup() {
        when (val response = catalogRepository.getAllByGroup(group.value.id)) {
            is ResultApi.WithData -> {
                listCatalog = response.data as ArrayList<CatalogDto>
            }

            else -> {
                resultChannel.send(GroupModelEvent.Message("Ошибка"))
            }
        }
    }

    private fun updateListCatalog() = viewModelScope.launch {
        updateListCatalogByGroup()
    }


    private fun deleteCatalog(id: Long) = viewModelScope.launch {

        when (catalogRepository.delete(id)) {
            is ResultApi.WithData -> {
                resultChannel.send(GroupModelEvent.Message("Каталог удален"))
                //TODO: Обновление списка каталогов
                updateListCatalog()

            }

            else -> {
                resultChannel.send(GroupModelEvent.Message("Ошибка"))
            }
        }
    }

    private fun createCatalog(name: String, groupId: Long) = viewModelScope.launch {

        when (catalogRepository.add(name, groupId)) {
            is ResultApi.WithData -> {
                resultChannel.send(GroupModelEvent.Message("Успешное добавление $name"))
                //TODO: Обновление списка каталогов
                updateListCatalog()
            }

            else -> {
                resultChannel.send(GroupModelEvent.Message("Ошибка"))
            }
        }
    }

    private fun loadGroup(groupId: Long) = viewModelScope.launch {

        when (val response = groupRepository.get(groupId)) {
            is ResultApi.WithData -> {
                group.value = response.data ?: group.value
                //TODO: Обновление списка каталогов
                updateListCatalog()
            }

            else -> {
                resultChannel.send(GroupModelEvent.Message("Ошибка"))
            }
        }
    }
}