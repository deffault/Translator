package com.example.translator.domain.interactor

import com.example.translator.AppState
import com.example.translator.domain.entity.Word
import com.example.translator.domain.repositories.Repository
import io.reactivex.rxjava3.core.Single

class MainInteractor(
    private val repository: Repository<List<Word>>
) : BaseInteractor<AppState> {
    override fun getData(searchingWord: String): Single<AppState> {
        return repository.getData(searchingWord = searchingWord).map { wordList ->
            AppState.Success(data = wordList)
        }
    }
}