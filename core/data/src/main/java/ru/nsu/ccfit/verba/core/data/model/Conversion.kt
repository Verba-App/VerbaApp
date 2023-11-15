package ru.nsu.ccfit.verba.core.data.model

import ru.nsu.ccfit.verba.core.model.Catalog
import ru.nsu.ccfit.verba.core.model.Group
import ru.nsu.ccfit.verba.core.model.Result
import ru.nsu.ccfit.verba.core.network.dto.CatalogDto
import ru.nsu.ccfit.verba.core.network.dto.GroupDto
import ru.nsu.ccfit.verba.core.network.dto.Response
import ru.nsu.ccfit.verba.core.network.dto.isSuccess


fun CatalogDto.toModel(): Catalog {
    return Catalog(id, name)
}

fun GroupDto.toModel(): Group {
    return Group(id, name)
}

fun Response<CatalogDto>.toResultCatalog(): Result<Catalog> {
    return if (isSuccess()) {
        Result.Success(data!!.toModel())
    } else {
        Result.Error(message!!)
    }
}

fun Response<String>.toResultString(): Result<String> {
    return if (isSuccess()) {
        Result.Success(data!!)
    } else {
        Result.Error(message!!)
    }
}

fun Response<GroupDto>.toResultGroup(): Result<Group> {
    return if (isSuccess()) {
        Result.Success(data!!.toModel())
    } else {
        Result.Error(message!!)
    }
}

fun Response<Unit>.toUnitResult(): Result<Unit> {
    return if (isSuccess()) {
        Result.Success(Unit)
    } else {
        Result.Error(message!!)
    }
}

fun Response<List<GroupDto>>.toListGroupResult(): Result<List<Group>> {
    return if (isSuccess()) {
        Result.Success(data!!.map { it.toModel() })
    } else {
        Result.Error(message!!)
    }
}

fun Response<List<CatalogDto>>.toListCatalogResult(): Result<List<Catalog>> {
    return if (isSuccess()) {
        Result.Success(data!!.map { it.toModel() })
    } else {
        Result.Error(message!!)
    }
}

