package ru.nsu.ccfit.verbaapp.di

import retrofit2.Retrofit
import ru.nsu.ccfit.verbaapp.api.data.service.CatalogService
import javax.inject.Inject

class CatalogServiceProvider @Inject constructor(retrofit: Retrofit) {
    val catalogService: CatalogService = retrofit.create(CatalogService::class.java)
}