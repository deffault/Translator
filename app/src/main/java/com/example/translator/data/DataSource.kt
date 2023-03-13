package com.example.translator.data

import io.reactivex.rxjava3.core.Single

interface DataSource<T: Any> {
    fun getData(searchingWord: String): Single<T>
}