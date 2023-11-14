package ru.nsu.ccfit.verba.feature.detailsgroup

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nsu.ccfit.verba.core.model.Catalog

@Composable
fun DetailGroupScreen(
    viewModel: DetailGroupViewModel = hiltViewModel(),
    chooseCatalog: (catalog: Catalog) -> Unit
) {
    val context = LocalContext.current

}