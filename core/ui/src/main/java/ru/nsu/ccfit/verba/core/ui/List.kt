package ru.nsu.ccfit.verba.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DropdownMenu
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@Composable
fun <T> ListView(items: List<T>, onClick: (T) -> Unit, onDelete: (T) -> Unit,context: @Composable (T)->Unit) {
    LazyColumn {
        items(items) { item ->
            val expanded = remember { mutableStateOf(false) }

            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxWidth()
                    .clickable { onClick(item) },

                elevation = 4.dp
            ) {
                Row(
                    modifier = Modifier.padding(8.dp)
                ) {
                    Row(Modifier.weight(5f)) {
                        context.invoke(item)
                    }

                    Box(Modifier.weight(1f)) {

                        IconButton(
                            modifier = Modifier.align(Alignment.TopEnd),
                            onClick = { expanded.value = true }

                        ) {
                            Icon(Icons.Default.MoreVert, contentDescription = "More options")
                        }

                        DropdownMenu(
                            expanded = expanded.value,
                            modifier = Modifier
                                .width(100.dp)
                                .clickable { onDelete.invoke(item) },
                            // offset = DpOffset((-30).dp, 0.dp),
                            onDismissRequest = { expanded.value = false }
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Delete",
                                    tint = Color.Red
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Text("Delete")
                            }
                        }
                    }


                }
            }
        }
    }
}

