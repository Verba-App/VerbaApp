package ru.nsu.ccfit.verbaapp.di

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class JwtInterceptor @Inject constructor(private val jwtTokenProvider: JwtTokenProvider) :
    Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val jwtToken = jwtTokenProvider.getJwtToken() ?: return chain.proceed(chain.request())

        val request = chain.request().newBuilder()
            .addHeader("Authorization", "Bearer $jwtToken")
            .build()
        return chain.proceed(request)
    }
}

