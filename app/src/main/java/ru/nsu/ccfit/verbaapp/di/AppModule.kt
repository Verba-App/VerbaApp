package ru.nsu.ccfit.verbaapp.di

import android.app.Application
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import ru.nsu.ccfit.verbaapp.api.auth.service.AuthApi
import ru.nsu.ccfit.verbaapp.core.auth.repo.AuthRepository
import ru.nsu.ccfit.verbaapp.core.auth.repo.impl.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val request = chain.request()
            val response = chain.proceed(request)
            if (response.code() == 500) {
                // handle the HTTP 500 error manually
                // for example, show an error message to the user
                // or return an error response object
                // instead of throwing an HttpException
                response.newBuilder().code(200).build()
            } else {
                response
            }
        }
        .build()

    @Provides
    @Singleton
    fun provideAuthApi(): AuthApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.109:8090/")
            .client(okHttpClient)
            //.addConverterFactory(MoshiConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create()
    }

    @Provides
    @Singleton
    fun provideSharedPref(app: Application): SharedPreferences {
        return app.getSharedPreferences("prefs", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }
}