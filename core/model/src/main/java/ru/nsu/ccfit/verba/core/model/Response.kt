package ru.nsu.ccfit.verba.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Response<R>(
    val code: Code,
    val message: String?,
    val data: R?
)

fun  Response<*>.isSuccess(): Boolean{
    return code == Code.OK
}
fun <R> Response<R>.onSuccess(action: (R) -> Unit): Response<R> {
    if (code == Code.OK) {
        action(data!!)
    }
    return this
}


fun Response<*>.onFailure(action: (code: Code, message:String) -> Unit): Response<*> {
    if (data == null && message!=null) {
        action(code,message)
    }
    return this
}

