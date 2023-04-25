package ru.nsu.ccfit.verbaapp.ui.screen.main

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextButton

import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.flow.collect
import ru.nsu.ccfit.verbaapp.R
import ru.nsu.ccfit.verbaapp.ui.component.DefaultAddedMenu
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import ru.nsu.ccfit.verbaapp.api.data.dto.GroupDto
import ru.nsu.ccfit.verbaapp.ui.component.ListView


@Composable
@Destination
fun MainScreen(
    navigator: DestinationsNavigator,
    viewModel: MainViewModel = hiltViewModel()
) {
    val listGroup = viewModel.listGroup

    val dialogCreate = remember {
        mutableStateOf(false)
    }

    val filter = remember {
        mutableStateOf(false)
    }
    DefaultAddedMenu(
        stringResource(id = R.string.group),
        bottomAppBar = {
            IconButton(onClick = {
                filter.value = !filter.value
                viewModel.onEvent(MainUiEvent.AvalableMain(filter.value))
            }) {
                if (filter.value){
                    Icon(
                        painter = painterResource(R.drawable.filter_icon),
                        contentDescription = "Filter",
                        tint = Color.White
                    )
                }else{
                    Icon(
                        painter = painterResource(R.drawable.filter_icon),
                        contentDescription = "Filter"
                    )
                }

            }
            IconButton(onClick = { viewModel.onEvent(MainUiEvent.RefreshMain) }) {
                Icon(
                    painter = painterResource(R.drawable.refresh_icon),
                    contentDescription = "Refresh"
                )
            }

        },
        onAddEvent = {
            dialogCreate.value = true


        }) {
        val context = LocalContext.current
        LaunchedEffect(viewModel, context) {
            viewModel.event.collect { result ->
                when (result) {
                    is MainViewModelEvent.Message -> {
                        Toast.makeText(
                            context,
                            result.value,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is MainViewModelEvent.OpenGroup -> {


                        navigator.navigate("groupScreen/${result.value.id}")
                    }
                }

            }
        }
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            GroupListView(listGroup, onClick = {
                viewModel.onEvent(MainUiEvent.ViewMain(it))
            }, onDelete = {
                viewModel.onEvent(MainUiEvent.DeleteMain(it))
            })

            if (dialogCreate.value) {
                CreateGroupDialog(onGroupCreated = { name ->
                    viewModel.onEvent(
                        MainUiEvent.CreateMain(
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

@Composable
fun GroupListView(items: List<GroupDto>, onClick: (GroupDto) -> Unit, onDelete: (GroupDto) -> Unit) {

    ListView(items = items, onClick = onClick , onDelete = onDelete ) {
        Image(
            painter = painterResource(R.drawable.image_icon),
            contentDescription = it.name,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )
        Column{
            Text(text = it.name, modifier = Modifier.align( Alignment.CenterHorizontally))
        }
    }
}
