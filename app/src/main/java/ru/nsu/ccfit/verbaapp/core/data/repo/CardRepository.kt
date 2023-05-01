package ru.nsu.ccfit.verbaapp.core.data.repo

import ru.nsu.ccfit.verbaapp.api.data.dto.CardDto
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi

interface CardRepository {
    suspend fun getAllCardByCatalog(catalogId: Long): ResultApi<List<CardDto>>
}