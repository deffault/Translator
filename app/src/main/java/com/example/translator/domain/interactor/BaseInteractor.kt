package com.example.translator.domain.interactor

import io.reactivex.rxjava3.core.Single

interface BaseInteractor<T: Any> {
    fun getData(searchingWord: String): Single<T>
}