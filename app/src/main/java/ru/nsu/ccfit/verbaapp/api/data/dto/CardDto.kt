package ru.nsu.ccfit.verbaapp.api.data.dto


import java.io.Serializable
import java.time.OffsetDateTime

enum class CardType {
    PHOTO,
    QUOTE
}

open class CardDto(
    open var id: Long,
    open var createDate: String,
    open var type: CardType,
    open var word: WordDto,
) : Serializable