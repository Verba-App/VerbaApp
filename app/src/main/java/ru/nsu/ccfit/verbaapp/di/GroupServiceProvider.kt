package ru.nsu.ccfit.verbaapp.di

import retrofit2.Retrofit
import ru.nsu.ccfit.verbaapp.api.data.service.GroupService
import javax.inject.Inject

class GroupServiceProvider @Inject constructor(retrofit: Retrofit) {

    val groupService: GroupService = retrofit.create(GroupService::class.java)
}