package ru.nsu.ccfit.verba.core.data.local

interface TokenRepository {
    fun getToken(): String
    fun setToken(token: String)
}