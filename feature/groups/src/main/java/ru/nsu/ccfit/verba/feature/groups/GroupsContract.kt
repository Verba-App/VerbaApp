package ru.nsu.ccfit.verba.feature.groups

import ru.nsu.ccfit.verba.core.model.Group

data class GroupsState(
    var groups: List<Group> = ArrayList()
)

sealed interface GroupsUiState {
    data object SuccessDeleteGroup : GroupsUiState
    data object SuccessCreateGroup : GroupsUiState
    class Error(val message: String) : GroupsUiState
}

sealed class GroupsUiEvent {
    data class CreateGroup(val name: String) : GroupsUiEvent()
    data object UpdateListGroups : GroupsUiEvent()
    data class DeleteGroup(val value: Group) : GroupsUiEvent()
}