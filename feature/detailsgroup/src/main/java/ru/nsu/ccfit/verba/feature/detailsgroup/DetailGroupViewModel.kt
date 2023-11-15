package ru.nsu.ccfit.verba.feature.detailsgroup

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

class DetailGroupViewModel
@AssistedInject constructor(
    @Assisted groupId: Long
) : ViewModel() {





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