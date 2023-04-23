package ru.nsu.ccfit.verbaapp.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun DefaultAddedMenu(@StringRes nameId: Int, onAddEvent: ()->Unit, bottomAppBar : @Composable () -> Unit = {},content : @Composable (PaddingValues) -> Unit){
    Scaffold(
        topBar = {
            Text(
                text = stringResource(id = nameId),
                textAlign = TextAlign.Center,
                color = Color.White,
                style = MaterialTheme.typography.h2,
                modifier = Modifier
                    .wrapContentWidth().padding(start = 20.dp)
            )
        },
        bottomBar = {
            BottomAppBar(
                backgroundColor = MaterialTheme.colors.background,
                cutoutShape = CircleShape
            ) {
                bottomAppBar.invoke()

//                IconButton(onClick = {}) {
//                    Icon(painter = painterResource(R.drawable.filter_icon), contentDescription ="df")
//                }
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