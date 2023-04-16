package ru.nsu.ccfit.verbaapp.api.component

class Response<T> {
    var code: Code? = null
    var message: String? = null
    var data: T? = null
}
