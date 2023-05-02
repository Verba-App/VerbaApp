package ru.nsu.ccfit.verbaapp.api.data.service

import retrofit2.http.GET
import retrofit2.http.Query
import ru.nsu.ccfit.verbaapp.api.component.Response

interface DataService {

    @GET("/data/image/found")
    suspend fun searchImage(
        @Query("search")  text: String
    ): Response<List<String>>
}