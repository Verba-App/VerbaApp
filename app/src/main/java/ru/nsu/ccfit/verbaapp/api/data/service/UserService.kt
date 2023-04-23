package ru.nsu.ccfit.verbaapp.api.data.service

import retrofit2.http.*
import ru.nsu.ccfit.verbaapp.api.component.Response
import ru.nsu.ccfit.verbaapp.api.data.dto.UserDto

interface UserService {

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Long): Response<UserDto>
}