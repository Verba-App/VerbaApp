package ru.nsu.ccfit.verbaapp.api.data.service

import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import ru.nsu.ccfit.verbaapp.api.component.Response
import ru.nsu.ccfit.verbaapp.api.data.dto.CatalogDto

interface CatalogService {
    @POST("api/catalog/create")
    suspend fun createCatalog(
        @Query("name") name: String,
        @Query("group-id") groupId: Long

    ): Response<Void>

    @POST("api/catalog/delete/{id}")
    suspend fun deleteCatalog(
        @Path("id") id: Long
    ): Response<Void>


    @POST("api/catalog/{id}")
    suspend fun getCatalog(
        @Path("id") id: Long
    ): Response<CatalogDto>

    @GET("api/catalog/all/group/{id}")
    suspend fun getAllCatalogByGroup(
        @Path("id") id: Long
    ): Response<List<CatalogDto>>
}