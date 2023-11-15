package ru.nsu.ccfit.verba.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import dagger.hilt.android.EntryPointAccessors
import ru.nsu.ccfit.verba.activity.MainActivity.ViewModelFactoryProvider
import ru.nsu.ccfit.verba.feature.detailsgroup.DetailGroupViewModel

interface BaseViewModelFactoryProvider {
    fun getDetailsGroupViewModelFactory(): DetailGroupViewModel.Factory
}

@Composable
fun detailGroupViewModel(groupId: Long): DetailGroupViewModel = viewModel(
    factory = DetailGroupViewModel.provideFactory(
        getViewModelFactoryProvider().getDetailsGroupViewModelFactory(),
        groupId
    )
)

@Composable
private fun getViewModelFactoryProvider() = EntryPointAccessors.fromActivity(
    LocalContext.current as Activity,
    ViewModelFactoryProvider::class.java
)

