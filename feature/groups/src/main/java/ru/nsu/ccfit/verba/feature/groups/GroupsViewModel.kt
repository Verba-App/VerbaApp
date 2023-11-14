package ru.nsu.ccfit.verba.feature.groups

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
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
    private val _updateUiState =
        MutableStateFlow<GroupsUiState>(GroupsUiState.Nothing)
    val updateUiState = _updateUiState.asStateFlow()

    init {
        getAllGroup()
    }

    fun onEvent(event: GroupsUiEvent) {
        _updateUiState.value = GroupsUiState.Nothing
        when (event) {
            is GroupsUiEvent.ChooseGroup -> _updateUiState.value = GroupsUiState.OpenGroup(event.value)
            is GroupsUiEvent.CreateGroup -> createGroup(event.name)
            is GroupsUiEvent.DeleteGroup -> deleteGroup(event.value)
        }
    }

    private fun getAllGroup() =
        viewModelScope.launch {
            when (val result = getAllGroupsUserUseCase()) {
                is Result.Error -> GroupsUiState.Error(result.message)
                is Result.Success -> {
                    dataState.groups = result.data
                }
            }
        }

    private fun createGroup(name: String) =
        viewModelScope.launch {
            _updateUiState.value = when (val result = createGroupUseCase(name)) {
                is Result.Error -> GroupsUiState.Error(result.message)
                is Result.Success -> GroupsUiState.SuccessCreateGroup
            }
        }


    private fun deleteGroup(group: Group) =
        viewModelScope.launch {
            _updateUiState.value = when (val result = deleteGroupUseCase(group)) {
                is Result.Error -> GroupsUiState.Error(result.message)
                is Result.Success -> GroupsUiState.SuccessCreateGroup
            }
        }

    data class GroupsState(
        var groups: List<Group> = ArrayList()
    )

    sealed interface GroupsUiState {
        data object SuccessDeleteGroup : GroupsUiState
        data object SuccessCreateGroup : GroupsUiState
        class OpenGroup(val group: Group) : GroupsUiState
        class Error(val message: String) : GroupsUiState
        data object Nothing : GroupsUiState
    }
}



