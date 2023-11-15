package ru.nsu.ccfit.verba.feature.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.ui.DefaultAddedMenu
import ru.nsu.ccfit.verba.core.ui.VerbaErrorToast
import ru.nsu.ccfit.verba.core.ui.VerbaSuccessToast
import ru.nsu.ccfit.verba.core.ui.group.GroupListView


@Composable
fun GroupsScreen(
    viewModel: GroupsViewModel = hiltViewModel(),
    chooseGroup: (groupId: Long) -> Unit
) {
    val context = LocalContext.current

    val dataState =
        viewModel.dataState

    val dialogCreate = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(viewModel, context) {
        viewModel.stateUi.collect { result ->
            when (result) {
                is GroupsViewModel.GroupsUiState.Error -> VerbaErrorToast(context, result.message)
                GroupsViewModel.GroupsUiState.SuccessCreateGroup -> VerbaSuccessToast(context, "SuccessCreateGroup")
                GroupsViewModel.GroupsUiState.SuccessDeleteGroup -> VerbaSuccessToast(context, "SuccessDeleteGroup")
            }
        }
    }

    DefaultAddedMenu(
        stringResource(id = R.string.groups),
        onAddEvent = {
            dialogCreate.value = true
        }) {
        val context = LocalContext.current
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            GroupListView(
                dataState.groups,
                defaultPicture = painterResource(R.drawable.image_icon),
                onClick = {
                    chooseGroup.invoke(it.id)
                },
                onDelete = {
                    viewModel.onEvent(GroupsUiEvent.DeleteGroup(it))
                })

            if (dialogCreate.value) {
                CreateGroupDialog(onGroupCreated = { name ->
                    viewModel.onEvent(
                        GroupsUiEvent.CreateGroup(
                            name
                        )
                    )
                },
                    onDismissRequest = { dialogCreate.value = false })
            }

        }
    }


}


@Composable
fun CreateGroupDialog(
    onGroupCreated: (String) -> Unit,
    onDismissRequest: () -> Unit = {}
) {
    val groupName = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismissRequest
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Create a new group",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = groupName.value,
                onValueChange = { groupName.value = it },
                label = { Text("Group name") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onDismissRequest,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        onGroupCreated(groupName.value)
                        onDismissRequest.invoke()
                    }
                ) {
                    Text("Create")
                }
            }
        }
    }
}

sealed class GroupsUiEvent {
    data class CreateGroup(val name: String) : GroupsUiEvent()
    data object UpdateListGroups : GroupsUiEvent()
    data class DeleteGroup(val value: Group) : GroupsUiEvent()
    // data class ChooseGroup(val value: Group) : GroupsUiEvent()
}