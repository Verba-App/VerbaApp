package ru.nsu.ccfit.verba.feature.groups

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nsu.ccfit.verba.core.ui.CreateDialog
import ru.nsu.ccfit.verba.core.ui.DefaultAddedMenu
import ru.nsu.ccfit.verba.core.ui.VerbaErrorToast
import ru.nsu.ccfit.verba.core.ui.VerbaSuccessToast
import ru.nsu.ccfit.verba.core.ui.group.GroupListView


@Composable
fun GroupsScreen(
    viewModel: GroupsViewModel = hiltViewModel(), chooseGroup: (groupId: Long) -> Unit
) {
    val context = LocalContext.current

    val dataState = viewModel.dataState

    val dialogCreate = remember {
        mutableStateOf(false)
    }

    LaunchedEffect(viewModel, context) {
        viewModel.stateUi.collect { result ->
            when (result) {
                is GroupsUiState.Error -> VerbaErrorToast(context, result.message)
                GroupsUiState.SuccessCreateGroup -> VerbaSuccessToast(context, "SuccessCreateGroup")
                GroupsUiState.SuccessDeleteGroup -> VerbaSuccessToast(context, "SuccessDeleteGroup")
            }
        }
    }

    DefaultAddedMenu(stringResource(id = R.string.groups), onAddEvent = {
        dialogCreate.value = true
    }) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            GroupListView(dataState.groups,
                defaultPicture = painterResource(R.drawable.image_icon),
                onClick = {
                    chooseGroup.invoke(it.id)
                },
                onDelete = {
                    viewModel.onEvent(GroupsUiEvent.DeleteGroup(it))
                })

            if (dialogCreate.value) {
                CreateDialog(title = stringResource(id = R.string.create_group), onCreated = { name ->
                    viewModel.onEvent(
                        GroupsUiEvent.CreateGroup(
                            name
                        )
                    )
                }, onDismiss = { dialogCreate.value = false })
            }

        }
    }


}

