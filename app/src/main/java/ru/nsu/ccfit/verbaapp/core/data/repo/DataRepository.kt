package ru.nsu.ccfit.verbaapp.core.data.repo

import ru.nsu.ccfit.verbaapp.api.data.dto.ImageDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi

interface DataRepository {
    suspend fun searchImage(text:String): ResultApi<List<ImageDto>>
}