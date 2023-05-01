package ru.nsu.ccfit.verbaapp.ui.screen.catalog

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.flow.collect
import ru.nsu.ccfit.verbaapp.R
import ru.nsu.ccfit.verbaapp.api.data.dto.CardDto
import ru.nsu.ccfit.verbaapp.ui.component.DefaultAddedMenu
import ru.nsu.ccfit.verbaapp.ui.component.ListView

@Composable
@Destination(route = "catalogScreen")
fun CatalogScreen(
    navigator: DestinationsNavigator,
    viewModel: CatalogViewModel = hiltViewModel(),
    catalogId: Long
) {

    val catalog = remember { viewModel.catalog }

    viewModel.onEvent(CatalogUiEvent.LoadCatalog(catalogId))

    DefaultAddedMenu(
        "Catalog",
        onAddEvent = {
          TODO("Добавление каточки")
        }) {

        val context = LocalContext.current
        LaunchedEffect(viewModel, context) {
            viewModel.event.collect { result ->
                when (result) {
                    is CatalogModelEvent.Message -> {
                        Toast.makeText(
                            context,
                            result.value,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        border = BorderStroke(2.dp, MaterialTheme.colors.background),
                        shape = RectangleShape,
                    )
                    .padding(10.dp)
                    .height(80.dp)
            ) {
                Image(
                    painter = painterResource(R.drawable.image_icon),
                    contentDescription = "image",
                    modifier = Modifier
                        .weight(1.5f)
                        .padding(end = 16.dp),
                    contentScale = ContentScale.Crop
                )
                Column(
                    Modifier.weight(2f)
                ) {
                    Text(
                        text = catalog.value.name,
                        color = Color.Black,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
                Button(
                    modifier = Modifier.weight(1f),
                    onClick = { viewModel.onEvent(CatalogUiEvent.StudyCatalog) }) {
                    Text(text = "Study", color = Color.White)
                }
            }

            Text(
                text = stringResource(id = R.string.word_list),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp)
            )


            CardListView(viewModel.cards, onClick = {
                //  viewModel.onEvent(GroupUiEvent.OpenCatalog(it))
            }, onDelete = {
                //     viewModel.onEvent(GroupUiEvent.DeleteCatalog(it))
            })


        }
    }
}

@Composable
fun CardListView(
    items: List<CardDto>,
    onClick: (CardDto) -> Unit,
    onDelete: (CardDto) -> Unit
) {
    ListView(items = items, onClick = onClick, onDelete = onDelete) {
        Column {
            Text(text = it.word.text, modifier = Modifier.align(Alignment.CenterHorizontally))
        }
    }
}


