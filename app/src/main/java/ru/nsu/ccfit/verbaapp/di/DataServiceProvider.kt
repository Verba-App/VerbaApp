package ru.nsu.ccfit.verbaapp.di

import retrofit2.Retrofit
import ru.nsu.ccfit.verbaapp.api.data.service.DataService
import javax.inject.Inject

class DataServiceProvider @Inject constructor(retrofit: Retrofit) {
    val dataService: DataService = retrofit.create(DataService::class.java)
}