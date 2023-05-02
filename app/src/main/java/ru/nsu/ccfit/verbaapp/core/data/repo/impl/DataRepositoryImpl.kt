package ru.nsu.ccfit.verbaapp.core.data.repo.impl

import ru.nsu.ccfit.verbaapp.api.data.dto.ImageDto
import ru.nsu.ccfit.verbaapp.api.data.service.DataService
import ru.nsu.ccfit.verbaapp.core.data.domen.ResultApi
import ru.nsu.ccfit.verbaapp.core.data.repo.DataRepository

class DataRepositoryImpl(
    private val service: DataService
) : DataRepository {
    override suspend fun searchImage(text: String): ResultApi<List<ImageDto>> {
        /*
        TODO: Слишком много ошибок на стороне сервера
        val response = service.searchImage(text)
        return when (response.code) {
               Code.OK -> ResultApi.WithData(response.data?.map { ImageDto(it) })
               else -> ResultApi.WithError()
           }
         */


        return ResultApi.WithData(listOf(
            ImageDto("https://webmg.ru/wp-content/uploads/2020/12/5-10.jpg"),
            ImageDto("https://webmg.ru/wp-content/uploads/2020/12/5-10.jpg"),
            ImageDto("https://webmg.ru/wp-content/uploads/2020/12/5-10.jpg"),
            ImageDto("https://webmg.ru/wp-content/uploads/2020/12/5-10.jpg"),
            ImageDto("https://webmg.ru/wp-content/uploads/2020/12/5-10.jpg")
        ))
    }


}