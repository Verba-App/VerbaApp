package ru.nsu.ccfit.verbaapp.di

import retrofit2.Retrofit
import ru.nsu.ccfit.verbaapp.api.data.service.CardService
import javax.inject.Inject

class CardServiceProvider @Inject constructor(retrofit: Retrofit) {
    val catalogService: CardService = retrofit.create(CardService::class.java)
}