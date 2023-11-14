package ru.nsu.ccfit.verba.core.data.local.impl

import android.content.SharedPreferences
import ru.nsu.ccfit.verba.core.data.local.TokenRepository
import javax.inject.Inject

class TokenRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : TokenRepository {

    private val link = "jwt"
    override fun getToken(): String {
        return sharedPreferences.getString(link, "") ?: ""
    }

    override fun setToken(token: String) {
        sharedPreferences.edit().putString(link, token).apply()
    }

}