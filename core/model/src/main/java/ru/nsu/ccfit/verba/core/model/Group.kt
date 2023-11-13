package ru.nsu.ccfit.verba.core.model

import ru.nsu.ccfit.verba.core.model.dto.GroupDto

data class Group(val id: Long, val name: String)

fun GroupDto.toModel(): Group {
    return Group(id, name)
}

