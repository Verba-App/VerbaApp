package ru.nsu.ccfit.verba.feature.groups

import androidx.compose.runtime.mutableStateOf
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

    var dataState = mutableStateOf(GroupsState())
    private val _updateUiState =
        MutableStateFlow<GroupsUiState>(GroupsUiState.Nothing)
    val updateUiState = _updateUiState.asStateFlow()

    init {
        getAllGroup()
    }

    fun onEvent(event: GroupsUiEvent) {
        _updateUiState.value = GroupsUiState.Nothing
        when (event) {
            is GroupsUiEvent.ChooseGroup -> _updateUiState.value =
                GroupsUiState.OpenGroup(event.value)

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
        when (val result = getAllGroupsUserUseCase()) {
            is Result.Error -> GroupsUiState.Error(result.message)
            is Result.Success -> {
                dataState.value.groups = result.data
            }
        }
    }

    private fun getAllGroup() = viewModelScope.launch {
        refreshGroup()
    }

    private fun createGroup(name: String) =
        viewModelScope.launch {
            val result = createGroupUseCase(name)
            refreshGroup()
            _updateUiState.value = when (result) {
                is Result.Error -> GroupsUiState.Error(result.message)
                is Result.Success -> GroupsUiState.SuccessCreateGroup
            }
        }


    private fun deleteGroup(group: Group) =
        viewModelScope.launch {
            val result = deleteGroupUseCase(group)
            refreshGroup()
            _updateUiState.value = when (result) {
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



