package ru.nsu.ccfit.verbaapp.di

import retrofit2.Retrofit
import ru.nsu.ccfit.verbaapp.api.auth.service.AuthApi
import javax.inject.Inject

class AuthServiceProvider @Inject constructor(retrofit: Retrofit) {

    val authApi: AuthApi = retrofit.create(AuthApi::class.java)
}