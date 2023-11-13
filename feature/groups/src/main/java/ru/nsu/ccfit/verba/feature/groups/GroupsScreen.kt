package ru.nsu.ccfit.verba.feature.groups

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nsu.ccfit.verba.core.model.Group

@Composable
fun GroupsScreen(viewModel: GroupsViewModel = hiltViewModel(), chooseGroup: (idGroup:Int) -> Unit) {
    val context = LocalContext.current

    val uiState by viewModel.updateUiState.collectAsState()

//    when (val state = uiState) {
//        is GroupsViewModel.AuthUiState.Error -> {VerbaErrorToast(context, stringResource(id = state.id))}
//        is GroupsViewModel.AuthUiState.SuccessSignIn -> successAuth.invoke()
//        is GroupsViewModel.AuthUiState.SuccessSignUp -> VerbaSuccessToast(context, stringResource(R.string.sign_in))
//        is GroupsViewModel.AuthUiState.Nothing -> Unit
//    }



}

sealed class GroupsUiEvent{
    data class CreateGroup(val name: String) : GroupsUiEvent()
    data class DeleteGroup(val value: Group) : GroupsUiEvent()
    data object RefreshGroups : GroupsUiEvent()
    data class ChooseGroup(val value: Group) : GroupsUiEvent()
}