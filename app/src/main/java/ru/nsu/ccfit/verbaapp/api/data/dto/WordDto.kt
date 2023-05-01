package ru.nsu.ccfit.verbaapp.api.data.dto

import java.io.Serializable

enum class WordType {
    VERB,
    NOUN
}

data class WordDto(
    var id: Long,
    var text: String,
    var transcription: String,
    var type: WordType,
    var categories: Set<CategoryDto>
) : Serializable