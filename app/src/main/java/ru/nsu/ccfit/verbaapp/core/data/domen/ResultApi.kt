package ru.nsu.ccfit.verbaapp.core.data.domen

sealed class ResultApi<T>(val data: T? = null) {
    class WithData<T>(data: T? = null): ResultApi<T>(data)
    class WithError<T>: ResultApi<T>()
}