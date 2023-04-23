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
import ru.nsu.ccfit.verbaapp.core.data.domen.ViewModelResult
import ru.nsu.ccfit.verbaapp.core.data.repo.GroupRepository
import javax.inject.Inject


@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: GroupRepository
) : ViewModel() {

    var listGroup by mutableStateOf(ArrayList<GroupDto>())
    private val resultChannel = Channel<GroupViewModelEvent>()
    val event = resultChannel.receiveAsFlow()


    init {
        refreshListGroup()
    }

    private fun refreshListGroup() =
        viewModelScope.launch {

            when (val response = repository.getAllMyGroup()) {
                is ViewModelResult.WithData -> {
                    listGroup = response.data as ArrayList<GroupDto>
                }

                else -> {
                    resultChannel.send(GroupViewModelEvent.Message("Ошибка"))
                }
            }
        }


    fun onEvent(event: GroupUiEvent) {
        when (event) {
            is GroupUiEvent.CreateGroup ->{
                addNewGroup(event.name)
                refreshListGroup()
            }

            is GroupUiEvent.DeleteGroup -> {
                deleteGroup(event.value)
                refreshListGroup()
            }
            is GroupUiEvent.ViewGroup -> {
              openGroup(event.value)
            }
        }
    }

    private fun openGroup(value: GroupDto)=  viewModelScope.launch  {
        resultChannel.send(GroupViewModelEvent.OpenGroup(value))
    }

    private fun addNewGroup(name: String) =
        viewModelScope.launch {

            when (repository.add(name)) {
                is ViewModelResult.WithData -> {
                    resultChannel.send(GroupViewModelEvent.Message("Группа $name создана"))
                }
                else -> {
                    resultChannel.send(GroupViewModelEvent.Message("Ошибка"))
                }
            }
        }


    private fun deleteGroup(groupDto: GroupDto) =
        viewModelScope.launch {

            when (repository.delete(groupDto.id)) {
                is ViewModelResult.WithData -> {
                    resultChannel.send(GroupViewModelEvent.Message("Группа ${groupDto.name} удалена"))
                }
                else -> {
                    resultChannel.send(GroupViewModelEvent.Message("Ошибка"))
                }
            }
        }
}

