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

    //Catalogs
    private const val catalog = "catalog"

    const val createCatalog = "$api/$catalog/create"

    fun deleteCatalog(id:Long) =
        "$api/$catalog/delete/$id"
    fun getCatalog(id:Long) =
        "$api/$catalog/$id"
    fun getAllCatalogByGroup(id:Long) =
        "$api/$catalog/all/group/$id"



}