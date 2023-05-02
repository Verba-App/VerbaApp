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
import ru.nsu.ccfit.verbaapp.api.data.service.CardService
import ru.nsu.ccfit.verbaapp.api.data.service.CatalogService
import ru.nsu.ccfit.verbaapp.api.data.service.DataService
import ru.nsu.ccfit.verbaapp.api.data.service.GroupService
import ru.nsu.ccfit.verbaapp.core.data.repo.CardRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.CatalogRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.DataRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.GroupRepository
import ru.nsu.ccfit.verbaapp.core.data.repo.impl.CardRepositoryImpl
import ru.nsu.ccfit.verbaapp.core.data.repo.impl.CatalogRepositoryImpl
import ru.nsu.ccfit.verbaapp.core.data.repo.impl.DataRepositoryImpl
import ru.nsu.ccfit.verbaapp.core.data.repo.impl.GroupRepositoryImpl
import java.util.concurrent.TimeUnit
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
            .connectTimeout(0, TimeUnit.MILLISECONDS)
            .readTimeout(0, TimeUnit.MILLISECONDS)
            .writeTimeout(0, TimeUnit.MILLISECONDS)
            .addInterceptor(jwtInterceptor).addInterceptor { chain ->
                val request = chain.request()
                val response = chain.proceed(request)
                if (response.code() != 200) {
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

    //Group

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

    //Catalog
    @Singleton
    fun provideCatalogServiceProvider(retrofit: Retrofit): CatalogServiceProvider {
        return CatalogServiceProvider(retrofit)
    }

    @Provides
    @Singleton
    fun provideCatalogService(catalogServiceProvider: CatalogServiceProvider): CatalogService {
        return catalogServiceProvider.catalogService
    }

    @Provides
    @Singleton
    fun provideCatalogRepository(service: CatalogService): CatalogRepository {
        return CatalogRepositoryImpl(service)
    }

    //Card

    @Singleton
    fun provideCardServiceProvider(retrofit: Retrofit): CardServiceProvider {
        return CardServiceProvider(retrofit)
    }

    @Provides
    @Singleton
    fun provideCardService(cardServiceProvider: CardServiceProvider): CardService {
        return cardServiceProvider.catalogService
    }

    @Provides
    @Singleton
    fun provideCardRepository(service: CardService): CardRepository {
        return CardRepositoryImpl(service)
    }

    //Data

    @Singleton
    fun provideDataServiceProvider(retrofit: Retrofit): DataServiceProvider {
        return DataServiceProvider(retrofit)
    }

    @Provides
    @Singleton
    fun provideDataService(dataServiceProvider: DataServiceProvider): DataService {
        return dataServiceProvider.dataService
    }

    @Provides
    @Singleton
    fun provideDataRepository(service: DataService): DataRepository {
        return DataRepositoryImpl(service)
    }

}