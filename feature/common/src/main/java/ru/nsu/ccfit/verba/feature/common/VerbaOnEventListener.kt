package ru.nsu.ccfit.verba.feature.common

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow


interface VerbaOnEventListener{

    val eventChannel
        get() = MutableSharedFlow<VerbaAppEvent>()
    val events: SharedFlow<VerbaAppEvent>
        get() = eventChannel.asSharedFlow()

    fun onEvent(uiEvent: VerbaUiEvent)
}