package com.example.translator.data

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface WordApi {
    @GET("words/search")
    fun getTranslation(
        @Query("search") searchingWord: String
    ): Single<List<WordResponseDto>>
}