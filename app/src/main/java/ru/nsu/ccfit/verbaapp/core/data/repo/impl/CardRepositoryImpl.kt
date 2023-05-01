package ru.nsu.ccfit.verbaapp.core.data.repo.impl

import ru.nsu.ccfit.verbaapp.api.component.Code
import ru.nsu.ccfit.verbaapp.api.data.dto.CardDto
import ru.nsu.ccfit.verbaapp.api.data.service.CardService
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi
import ru.nsu.ccfit.verbaapp.core.data.repo.CardRepository

class CardRepositoryImpl(
    private val service: CardService
) : CardRepository {
    override suspend fun getAllCardByCatalog(catalogId: Long): ResultApi<List<CardDto>> {
        val response = service.getAllCardByCatalog(catalogId)

        return when (response.code) {
            Code.OK -> ResultApi.WithData(response.data)
            else -> ResultApi.WithError()
        }
    }
}