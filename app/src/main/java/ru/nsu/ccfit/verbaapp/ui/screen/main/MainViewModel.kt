package ru.nsu.ccfit.verbaapp.ui.screen.main

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi
import ru.nsu.ccfit.verbaapp.core.data.repo.GroupRepository
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GroupRepository
) : ViewModel() {

    var listGroup by mutableStateOf(ArrayList<GroupDto>())
    private val resultChannel = Channel<MainModelEvent>()
    val event = resultChannel.receiveAsFlow()

    private var myGroup = false


    init {
        refreshListGroup()
    }

    private suspend fun updateGroup() {
        when (val response =
            if (myGroup) repository.getAllMyGroup() else repository.getAllAvailableGroup()
        ) {
            is ResultApi.WithData -> {
                listGroup = response.data as ArrayList<GroupDto>
            }

            else -> {
                resultChannel.send(MainModelEvent.Message("Ошибка"))
            }
        }

    }

    private fun refreshListGroup() =
        viewModelScope.launch {
            updateGroup()
        }


    fun onEvent(event: MainUiEvent) {
        when (event) {
            is MainUiEvent.CreateMain -> {
                addNewGroup(event.name)
            }

            is MainUiEvent.DeleteMain -> {
                deleteGroup(event.value)
            }

            is MainUiEvent.ViewMain -> {
                openGroup(event.value)
            }

            MainUiEvent.RefreshMain -> {
                refreshListGroup()
            }

            is MainUiEvent.AvalableMain -> {
                myGroup = event.filter
                refreshListGroup()
            }
        }
    }

    private fun openGroup(value: GroupDto) = viewModelScope.launch {
        resultChannel.send(MainModelEvent.OpenGroup(value))
    }

    private fun addNewGroup(name: String) =
        viewModelScope.launch {

            when (repository.add(name)) {
                is ResultApi.WithData -> {
                    resultChannel.send(MainModelEvent.Message("Группа $name создана"))
                    //TODO: Обновление списка
                    updateGroup()
                }

                else -> {
                    resultChannel.send(MainModelEvent.Message("Ошибка"))
                }
            }
        }


    private fun deleteGroup(groupDto: GroupDto) =
        viewModelScope.launch {

            when (repository.delete(groupDto.id)) {
                is ResultApi.WithData -> {
                    resultChannel.send(MainModelEvent.Message("Группа ${groupDto.name} удалена"))
                    //TODO: Обновление списка
                    updateGroup()
                }

                else -> {
                    resultChannel.send(MainModelEvent.Message("Ошибка"))
                }
            }
        }
}

