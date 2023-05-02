package ru.nsu.ccfit.verbaapp.ui.screen.card.create

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ContentAlpha
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import ru.nsu.ccfit.verbaapp.R
import ru.nsu.ccfit.verbaapp.api.data.dto.ImageDto
import ru.nsu.ccfit.verbaapp.ui.component.DefaultCloseMenu
import ru.nsu.ccfit.verbaapp.ui.component.MyEditTextField
import ru.nsu.ccfit.verbaapp.ui.component.MyTextField


@Composable
@Destination(route = "createCardScreen")
fun CreateCardScreen(
    navigator: DestinationsNavigator,
    viewModel: CreateCardViewModel = hiltViewModel(),
    catalogId: Long
) {

    DefaultCloseMenu(name = stringResource(R.string.create_card),
        onCloseEvent = { viewModel.onEvent(CreateCardUiEvent.Cancel) },
        onDoneEvent = { viewModel.onEvent(CreateCardUiEvent.CreateCard(catalogId)) }) {

        val word = remember { mutableStateOf("") }
        val transcription = remember { viewModel.transcription }

        val context = LocalContext.current
        LaunchedEffect(viewModel, context) {
            viewModel.event.collect { result ->
                when (result) {
                    is CreateCardModelEvent.CloseScreen -> {
                        navigator.popBackStack()
                    }

                    is CreateCardModelEvent.Message -> {
                        Toast.makeText(
                            context, result.value, Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                Row(Modifier.fillMaxWidth()) {
                    Column(modifier = Modifier.weight(4f)) {
                        Text(
                            text = "Word",
                            color = Color.Black,
                            style = MaterialTheme.typography.caption
                        )

                        MyEditTextField(value = word.value, onValueChange = { word.value = it })

                    }
                    Box(
                        modifier = Modifier.weight(1f), contentAlignment = Alignment.Center
                    ) {
                        IconButton(onClick = { viewModel.onEvent(CreateCardUiEvent.SearchWord(word.value)) }) {
                            Icon(
                                painterResource(R.drawable.search_icon),
                                tint = Color.Black,
                                modifier = Modifier.fillMaxWidth(),
                                contentDescription = "Search"
                            )

                        }
                    }

                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Transcription",
                    color = Color.Black,
                    style = MaterialTheme.typography.caption
                )

                MyTextField(modifier = Modifier.fillMaxWidth(), value = transcription)

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Choose better image",
                    color = Color.Black,
                    style = MaterialTheme.typography.caption
                )

                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(190.dp)
                        .border(
                            1.dp,
                            MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.disabled),
                            shape = MaterialTheme.shapes.small
                        )
                ) {
                    MyImageList(viewModel.listImage, onItemClicked = { viewModel.image = it })
                }
            }
        }
    }
}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun MyImageList(list: List<ImageDto>, onItemClicked: (ImageDto) -> Unit) {
    LazyVerticalGrid(
        cells = GridCells.Fixed(2), modifier = Modifier.padding(16.dp)
    ) {
        items(list) { card ->
            ListImageItem(item = card, onItemClicked = onItemClicked)
        }
    }
}


@Composable
fun ListImageItem(item: ImageDto, onItemClicked: (ImageDto) -> Unit) {
    Card(
        modifier = Modifier
            .padding(16.dp)
            .height(50.dp)

            .clickable(onClick = { onItemClicked(item) }), elevation = 4.dp
    ) {

        Image(
            painter = rememberImagePainter(item.imageUrl),
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )

    }
}
