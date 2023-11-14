package ru.nsu.ccfit.verba.core.network.di

import android.content.SharedPreferences
import android.util.Log
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.plugins.observer.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import ru.nsu.ccfit.verba.core.network.api.auth.AuthApi
import ru.nsu.ccfit.verba.core.network.api.auth.AuthApiImpl
import ru.nsu.ccfit.verba.core.network.api.groups.GroupsApi
import ru.nsu.ccfit.verba.core.network.api.groups.GroupsApiImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {
    @Provides
    fun getHttpClient(sharedPreferences: SharedPreferences): HttpClient {
        return HttpClient(Android) {
            install(DefaultRequest) {
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                val token = sharedPreferences.getString("jwt", "")
                if (token?.isNotEmpty() == true) {
                    header("Authorization", "Bearer $token")
                }

                url {
                    url("http://212.233.123.240")
                    port = 8080
                }
            }
            install(ContentNegotiation) {
                json(Json {
                    ignoreUnknownKeys = true
                    isLenient = true
                    encodeDefaults = false
                    classDiscriminator = "#class"
                })
                engine {
                    connectTimeout = 10_000
                    socketTimeout = 10_000
                }
            }

            install(Logging) {
                logger = object : Logger {
                    override fun log(message: String) {
                        Log.v("ktor_logger:", message)
                    }

                }
                level = LogLevel.ALL
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.d("http_status:", "${response.status.value}")
                }
            }
        }
    }

    @Singleton
    @Provides
    fun getAuthApiImpl(impl: AuthApiImpl): AuthApi = impl

    @Singleton
    @Provides
    fun getGroupsApiImpl(impl: GroupsApiImpl): GroupsApi = impl
}