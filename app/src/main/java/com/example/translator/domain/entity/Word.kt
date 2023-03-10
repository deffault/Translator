package com.example.translator.domain.entity

data class Word(
    val id: Int,
    val text: String,
    val meanings: List<Meaning>
)
data class Meaning(
    val soundUrl: String,
    val transcription: String,
    val translation: Translation
)

data class Translation(
    val translationText: String
)
