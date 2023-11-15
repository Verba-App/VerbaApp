package ru.nsu.ccfit.verba.feature.detailsgroup

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
import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.ui.CreateDialog
import ru.nsu.ccfit.verba.core.ui.DefaultAddedMenu
import ru.nsu.ccfit.verba.core.ui.VerbaErrorToast
import ru.nsu.ccfit.verba.core.ui.VerbaSuccessToast
import ru.nsu.ccfit.verba.core.ui.catalog.CatalogListView

@Composable
fun DetailGroupScreen(
    viewModel: DetailGroupViewModel = hiltViewModel(),
    chooseCatalog: (catalog: Catalog) -> Unit
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
                is DetailGroupUiState.Error -> VerbaErrorToast(context, result.message)
                DetailGroupUiState.SuccessCreateCatalog -> VerbaSuccessToast(
                    context,
                    "SuccessCreateCatalog"
                )

                DetailGroupUiState.SuccessDeleteCatalog -> VerbaSuccessToast(
                    context,
                    "SuccessDeleteCatalog"
                )
            }
        }
    }

    DefaultAddedMenu(
        dataState.group.name,
        onAddEvent = {
            dialogCreate.value = true
        }) {


        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {

            CatalogListView(
                dataState.catalogs,
                defaultPicture = painterResource(id = R.drawable.image_icon),
                onClick = {
                    chooseCatalog(it)
                },
                onDelete = {
                    viewModel.onEvent(GroupDetailUiEvent.DeleteCatalog(it))
                })

            if (dialogCreate.value) {
                CreateDialog(
                    title = stringResource(id = R.string.create_catalog),
                    onCreated = { name ->
                        viewModel.onEvent(GroupDetailUiEvent.CreateCatalog(name))
                    },
                    onDismiss = { dialogCreate.value = false })
            }
        }
    }

}


