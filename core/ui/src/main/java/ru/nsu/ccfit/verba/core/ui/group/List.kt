package ru.nsu.ccfit.verba.core.ui.group

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.ui.ListView

@Composable
fun GroupListView(
    items: List<Group>,
    defaultPicture: Painter,
    onClick: (Group) -> Unit,
    onDelete: (Group) -> Unit
) {

    ListView(items = items, onClick = onClick, onDelete = onDelete) {
        Image(
            painter = defaultPicture,
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