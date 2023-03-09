package com.example.translator.utils

import com.example.translator.data.MeaningDto
import com.example.translator.data.TranslationDto
import com.example.translator.data.WordResponseDto
import com.example.translator.domain.entity.Meaning
import com.example.translator.domain.entity.Translation
import com.example.translator.domain.entity.Word

fun WordResponseDto.toWord() = Word(
    text = text,
    meanings = meanings.map { dto -> dto.toMeaning() }
)

private fun MeaningDto.toMeaning() = Meaning(
    soundUrl = soundUrl,
    transcription = transcription,
    translation = translation.toTranslation()
)

private fun TranslationDto.toTranslation() = Translation(
    translationText = translationText
)