package ru.nsu.ccfit.verba.core.network.api

object HttpRoute {
    private const val api = "api"


    //Auth
    private const val auth = "auth"
    const val signIn =
        "$auth/token"

    const val signUp =
        "$auth/register"

    const val authenticate =
        "$auth/authenticate"

    //Groups
    private const val group = "group"
    fun getGroupById(id:Long) =
        "$api/$group/$id"

    const val createGroup= "$api/$group/create"

    fun deleteGroup(id:Long) =
        "$api/$group/$id/delete"

    const val getAllGroupsByUser= "$api/$group/all/user"

    const val getAllAvailableGroups= "$api/$group/all/available"

}