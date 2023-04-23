package ru.nsu.ccfit.verbaapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import ru.nsu.ccfit.verbaapp.api.auth.service.AuthApi
import ru.nsu.ccfit.verbaapp.core.auth.repo.AuthRepository
import ru.nsu.ccfit.verbaapp.core.auth.repo.impl.AuthRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nsu.ccfit.verbaapp.api.data.service.GroupService
import ru.nsu.ccfit.verbaapp.core.data.repo.GroupRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.impl.GroupRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("prefs", MODE_PRIVATE)
    }


    //jwt
    @Provides
    fun provideJwtTokenProvider(jwtTokenProvider: JwtTokenProvider): String? {
        return jwtTokenProvider.getJwtToken()
    }

    @Provides
    fun provideJwtInterceptor(jwtTokenProvider: JwtTokenProvider): JwtInterceptor {
        return JwtInterceptor(jwtTokenProvider)
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(jwtInterceptor: JwtInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(jwtInterceptor).addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                if (response.code() != 200) {
                    // handle the HTTP 500 error manually
                    response.newBuilder().code(200).build()
                } else {
                    response
                }
            }
            .build()
    }


    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://192.168.0.106:8080/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }



    //AUTH
    @Provides
    @Singleton
    fun provideAuthServiceProvider(retrofit: Retrofit): AuthServiceProvider {
        return AuthServiceProvider(retrofit)
    }

    @Provides
    @Singleton
    fun provideAuthApi(authApiProvider: AuthServiceProvider): AuthApi {
        return authApiProvider.authApi
    }


    @Provides
    @Singleton
    fun provideAuthRepository(api: AuthApi, prefs: SharedPreferences): AuthRepository {
        return AuthRepositoryImpl(api, prefs)
    }

    //Catalog

    @Provides
    @Singleton
    fun provideGroupServiceProvider(retrofit: Retrofit): GroupServiceProvider {
        return GroupServiceProvider(retrofit)
    }

    @Provides
    @Singleton
    fun provideGroupService(groupServiceProvider: GroupServiceProvider): GroupService {
        return groupServiceProvider.groupService
    }


    @Provides
    @Singleton
    fun provideGroupRepository(service: GroupService): GroupRepository {
        return GroupRepositoryImpl(service)
    }

}