package ru.nsu.ccfit.verba.core.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.nsu.ccfit.verba.core.data.local.TokenRepository
import ru.nsu.ccfit.verba.core.data.local.impl.TokenRepositoryImpl
import ru.nsu.ccfit.verba.core.data.remote.AuthRepository
import ru.nsu.ccfit.verba.core.data.remote.CatalogRepository
import ru.nsu.ccfit.verba.core.data.remote.GroupRepository
import ru.nsu.ccfit.verba.core.data.remote.impl.AuthRepositoryImpl
import ru.nsu.ccfit.verba.core.data.remote.impl.CatalogRepositoryImpl
import ru.nsu.ccfit.verba.core.data.remote.impl.GroupRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {
    @Binds
    @Singleton
    fun bindTokenRepository(impl: TokenRepositoryImpl): TokenRepository

    @Binds
    @Singleton
    fun bindCatalogRepository(impl: CatalogRepositoryImpl): CatalogRepository

    @Binds
    @Singleton
    fun bindAuthRepository(impl: AuthRepositoryImpl): AuthRepository

    @Binds
    @Singleton
    fun bindGroupRepository(impl: GroupRepositoryImpl): GroupRepository
}