package ru.nsu.ccfit.verba.core.model

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()

}

fun <T> Result<T>.onSuccess(action: (T) -> Unit) : Result<T> {
    if (this is Result.Success) {
        action(data)
    }
    return this
}

fun Result<*>.onError(action: (String) -> Unit) : Result<*>{
    if (this is Result.Error) {
        action(message)
    }
    return this
}