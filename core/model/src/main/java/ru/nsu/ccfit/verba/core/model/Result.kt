package ru.nsu.ccfit.verba.core.model

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()

}

suspend fun <T> Result<T>.onSuccess(action: suspend (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(data)
    }
    return this
}

suspend fun Result<*>.onError(action: suspend (String) -> Unit): Result<*> {
    if (this is Result.Error) {
        action(message)
    }
    return this
}