package ru.nsu.ccfit.verbaapp.api.data.service

import retrofit2.http.GET
import retrofit2.http.Path
import ru.nsu.ccfit.verbaapp.api.component.Response
import ru.nsu.ccfit.verbaapp.api.data.dto.CardDto

interface CardService {

    @GET("api/card/all/catalog/{id}")
    suspend fun getAllCardByCatalog(
        @Path("id") catalogId: Long
    ): Response<List<CardDto>>
}