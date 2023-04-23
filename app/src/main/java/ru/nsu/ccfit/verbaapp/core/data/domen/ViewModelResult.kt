package ru.nsu.ccfit.verbaapp.core.data.domen

sealed class ViewModelResult<T>(val data: T? = null) {
    class WithData<T>(data: T? = null): ViewModelResult<T>(data)
    class WithError<T>: ViewModelResult<T>()
}