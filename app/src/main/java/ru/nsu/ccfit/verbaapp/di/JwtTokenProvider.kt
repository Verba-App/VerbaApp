package ru.nsu.ccfit.verbaapp.di

import android.content.SharedPreferences
import javax.inject.Inject

class JwtTokenProvider @Inject constructor(private val sharedPreferences: SharedPreferences) {

    fun getJwtToken(): String? {
        return sharedPreferences.getString("jwt", null)
    }
}
