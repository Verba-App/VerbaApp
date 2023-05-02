package ru.nsu.ccfit.verbaapp.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DefaultAddedMenu(
    name: String,
    onAddEvent: () -> Unit,
    bottomAppBar: @Composable () -> Unit = {},
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            Text(
                text = name,
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(start = 20.dp)
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.background,
                cutoutShape = CircleShape
            ) {
                bottomAppBar.invoke()
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onAddEvent,
                content = {
                    Icon(Icons.Default.Add, contentDescription = "Add")
                },
                backgroundColor = MaterialTheme.colors.background
            )
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
        content = content
    )
}

@Composable
fun DefaultCloseMenu(
    name: String,
    onCloseEvent: () -> Unit,
    onDoneEvent: () -> Unit,
    content: @Composable (PaddingValues) -> Unit
) {
    Scaffold(
        topBar = {
            Row(modifier = Modifier.fillMaxWidth()) {

                Box(
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = onCloseEvent) {
                        Icon(Icons.Default.Close, contentDescription = "Close")
                    }
                }
                
                Text(
                    text = name,
                    textAlign = TextAlign.Center,
                    color = Color.White,
                    style = MaterialTheme.typography.h2,
                    modifier = Modifier
                        .wrapContentWidth()
                        .padding(start = 20.dp)
                )
            }

        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = onDoneEvent,
                content = {
                    Icon(Icons.Default.Done, contentDescription = "Done")
                },
                backgroundColor = MaterialTheme.colors.background
            )
        },
        content = content
    )
}