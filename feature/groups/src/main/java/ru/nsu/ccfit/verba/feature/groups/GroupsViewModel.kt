package ru.nsu.ccfit.verba.feature.groups

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.nsu.ccfit.verba.core.model.Group
import javax.inject.Inject

@HiltViewModel
class GroupsViewModel @Inject constructor(
) : ViewModel() {

    var dataState by mutableStateOf(GroupsState())
    private val _updateUiState =
        MutableStateFlow<GroupsUiState>(GroupsUiState.Nothing)
    val updateUiState = _updateUiState.asStateFlow()
    fun onEvent(event: GroupsUiEvent) {
        _updateUiState.value = GroupsUiState.Nothing
        when (event) {

            else -> {}
        }
    }



    data class GroupsState(
        val groups: ArrayList<Group> =  ArrayList()
    )

    sealed interface GroupsUiState {
        class Error(val id: Int) : GroupsUiState
        data object Nothing : GroupsUiState
    }
}



