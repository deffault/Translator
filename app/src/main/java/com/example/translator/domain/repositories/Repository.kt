package com.example.translator.domain.repositories

import io.reactivex.rxjava3.core.Single

interface Repository<T: Any> {
    fun getData(searchingWord: String): Single<T>
}