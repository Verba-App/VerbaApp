package ru.nsu.ccfit.verbaapp.ui.screen.group

import android.widget.Toast
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination

import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import ru.nsu.ccfit.verbaapp.R
import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto
import ru.nsu.ccfit.verbaapp.ui.component.DefaultAddedMenu
import ru.nsu.ccfit.verbaapp.ui.component.ListView
@Composable
@Destination(route = "groupScreen")
fun GroupScreen(
    navigator: DestinationsNavigator,
    viewModel: GroupViewModel = hiltViewModel(),
    groupId: Long
) {

    val dialogCreate = remember {
        mutableStateOf(false)
    }

    val group = remember { viewModel.group }

    viewModel.onEvent(GroupUiEvent.LoadGroup(groupId))

    DefaultAddedMenu(
        "Group: `${group.value.name}`",
        onAddEvent = {
            dialogCreate.value = true
        }) {

        val context = LocalContext.current
        LaunchedEffect(viewModel, context) {
            viewModel.event.collect { result ->
                when (result) {
                    is GroupModelEvent.Message -> {
                        Toast.makeText(
                            context,
                            result.value,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    is GroupModelEvent.OpenCatalog -> {
                        navigator.navigate("catalogScreen/${result.value.id}")
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            CatalogListView(viewModel.listCatalog, onClick = {
               viewModel.onEvent(GroupUiEvent.OpenCatalog(it))
            }, onDelete = {
                viewModel.onEvent(GroupUiEvent.DeleteCatalog(it))
            })

            if (dialogCreate.value) {
                CreateCatalogDialog(onCreated = { name ->
                    viewModel.onEvent(GroupUiEvent.CreateCatalog(name))
                },
                    onDismiss = { dialogCreate.value = false })
            }
        }
    }
}

@Composable
fun CatalogListView(
    items: List<CatalogDto>,
    onClick: (CatalogDto) -> Unit,
    onDelete: (CatalogDto) -> Unit
) {

    ListView(items = items, onClick = onClick, onDelete = onDelete) {
        Image(
            painter = painterResource(R.drawable.image_icon),
            contentDescription = it.name,
            modifier = Modifier
                .size(48.dp)
                .padding(end = 16.dp),
            contentScale = ContentScale.Crop
        )
        Column {
            Text(text = it.name, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}


@Composable
fun CreateCatalogDialog(
    onCreated: (String) -> Unit,
    onDismiss: () -> Unit = {}
) {
    val groupName = remember { mutableStateOf("") }

    Dialog(
        onDismissRequest = onDismiss
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            Text(
                text = "Create a new catalog",
                style = MaterialTheme.typography.h6
            )

            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = groupName.value,
                onValueChange = { groupName.value = it },
                label = { Text("Catalog name") }
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    onClick = onDismiss,
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Text("Cancel")
                }

                Button(
                    onClick = {
                        onCreated(groupName.value)
                        onDismiss.invoke()
                    }
                ) {
                    Text("Create")
                }
            }
        }
    }
}