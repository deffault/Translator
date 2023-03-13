package com.example.translator.data

import io.reactivex.rxjava3.core.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RemoteDateSource : DataSource<List<WordResponseDto>> {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://dictionary.skyeng.ru/api/public/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()

    private val api: WordApi = retrofit.create(WordApi::class.java)

    override fun getData(searchingWord: String): Single<List<WordResponseDto>> {
        return api.getTranslation(searchingWord = searchingWord)
    }
}