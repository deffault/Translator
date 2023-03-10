package com.example.translator.data

import com.google.gson.annotations.SerializedName

data class WordResponseDto(
    @SerializedName("id")
    val id: Int,

    @SerializedName("text")
    val text: String,

    @SerializedName("meanings")
    val meanings: List<MeaningDto>
)

data class MeaningDto(
    @SerializedName("soundUrl")
    val soundUrl: String,

    @SerializedName("transcription")
    val transcription: String,

    @SerializedName("translation")
    val translation: TranslationDto
)

data class TranslationDto(
    @SerializedName("text")
    val translationText: String
)
