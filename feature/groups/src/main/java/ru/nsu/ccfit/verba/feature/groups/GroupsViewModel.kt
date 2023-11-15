package ru.nsu.ccfit.verba.feature.groups

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.onError
import ru.nsu.ccfit.verba.core.model.onSuccess
import ru.nsu.ccfit.verba.domen.group.CreateGroupUseCase
import ru.nsu.ccfit.verba.domen.group.DeleteGroupUseCase
import ru.nsu.ccfit.verba.domen.group.GetAllGroupsUserUseCase
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
    val createGroupUseCase: CreateGroupUseCase,
    val deleteGroupUseCase: DeleteGroupUseCase,
    val getAllGroupsUserUseCase: GetAllGroupsUserUseCase
) : ViewModel() {

    var dataState by mutableStateOf(GroupsState())

    private val stateUiChannel = Channel<GroupsUiState>()
    val stateUi = stateUiChannel.receiveAsFlow()


    init {
        getAllGroup()
    }

    fun onEvent(event: GroupsUiEvent) {
        when (event) {
//            is GroupsUiEvent.ChooseGroup -> _updateUiState.value =
//                GroupsUiState.OpenGroup(event.value)

            is GroupsUiEvent.CreateGroup -> {
                createGroup(event.name)
            }

            is GroupsUiEvent.UpdateListGroups -> {
                getAllGroup()
            }

            is GroupsUiEvent.DeleteGroup -> {
                deleteGroup(event.value)
            }
        }
    }

    private suspend fun refreshGroup() {
        getAllGroupsUserUseCase()
            .onSuccess { dataState = dataState.copy(groups = it) }
            .onError { stateUiChannel.send(GroupsUiState.Error(it)) }
    }


    private fun getAllGroup() = viewModelScope.launch {
        refreshGroup()
    }

    private fun createGroup(name: String) =
        viewModelScope.launch {
            val result = createGroupUseCase(name)
            result
                .onSuccess {
                    refreshGroup()
                    stateUiChannel.send(GroupsUiState.SuccessCreateGroup)
                }
                .onError { stateUiChannel.send(GroupsUiState.Error(it)) }
        }


    private fun deleteGroup(group: Group) =
        viewModelScope.launch {
            val result = deleteGroupUseCase(group)
            refreshGroup()
            result
                .onSuccess {
                    refreshGroup()
                    stateUiChannel.send(GroupsUiState.SuccessDeleteGroup)
                }
                .onError { GroupsUiState.Error(it) }
        }
}



