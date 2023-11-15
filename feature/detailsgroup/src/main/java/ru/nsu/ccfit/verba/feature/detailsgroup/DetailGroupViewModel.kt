package ru.nsu.ccfit.verba.feature.detailsgroup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.onError
import ru.nsu.ccfit.verba.core.model.onSuccess
import ru.nsu.ccfit.verba.domen.catalog.CreateCatalogUseCase
import ru.nsu.ccfit.verba.domen.catalog.DeleteCatalogUseCase
import ru.nsu.ccfit.verba.domen.catalog.GetAllCatalogsByGroup
import ru.nsu.ccfit.verba.domen.group.GetGroupByIdUseCase

class DetailGroupViewModel
@AssistedInject constructor(
    @Assisted groupId: Long,
    val getGroupByIdUseCase: GetGroupByIdUseCase,
    val getAllCatalogsByGroup: GetAllCatalogsByGroup,
    val createCatalogUseCase: CreateCatalogUseCase,
    val deleteCatalogUseCase: DeleteCatalogUseCase
) : ViewModel() {

    var dataState by mutableStateOf(
        DetailGroupState(
            group = Group(groupId, "Group"),
            catalogs = ArrayList()
        )
    )

    private val stateUiChannel = Channel<DetailGroupUiState>()
    val stateUi = stateUiChannel.receiveAsFlow()

    init {
        getGroup(groupId)
        getAllCatalogs()
    }

    fun onEvent(event: GroupDetailUiEvent) {
        when (event) {
            is GroupDetailUiEvent.CreateCatalog -> createCatalog(event.name)
            is GroupDetailUiEvent.DeleteCatalog -> deleteCatalog(event.catalog)
            GroupDetailUiEvent.UpdateListCatalogs -> getAllCatalogs()
        }
    }


    private fun getAllCatalogs() = viewModelScope.launch {
        refreshListCatalog()
    }

    private fun createCatalog(name: String) = viewModelScope.launch {
        createCatalogUseCase(name, dataState.group)
            .onSuccess {
                refreshListCatalog()
                stateUiChannel.send(DetailGroupUiState.SuccessCreateCatalog) }
            .onError { stateUiChannel.send(DetailGroupUiState.Error(it)) }
    }

    private fun deleteCatalog(catalog: Catalog) = viewModelScope.launch {
        deleteCatalogUseCase(catalog)
            .onSuccess {
                refreshListCatalog()
                stateUiChannel.send(DetailGroupUiState.SuccessDeleteCatalog) }
            .onError { stateUiChannel.send(DetailGroupUiState.Error(it)) }
    }

    private suspend fun refreshListCatalog() {
        getAllCatalogsByGroup(dataState.group).onSuccess {
            dataState = dataState.copy(catalogs = it)
        }
    }

    private fun getGroup(groupId: Long) = viewModelScope.launch {
        getGroupByIdUseCase(groupId).onSuccess {
            dataState = dataState.copy(group = it)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(groupId: Long): DetailGroupViewModel
    }

    companion object {
        @Suppress("UNCHECKED_CAST")
        fun provideFactory(
            factory: Factory,
            groupId: Long,
        ) = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return factory.create(groupId) as T
            }
        }
    }
}
