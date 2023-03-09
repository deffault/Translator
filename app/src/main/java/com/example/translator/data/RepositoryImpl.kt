package com.example.translator.data

import com.example.translator.domain.entity.Word
import com.example.translator.domain.repositories.Repository
import com.example.translator.utils.toWord
import io.reactivex.rxjava3.core.Single

class RepositoryImpl(
    private val dataSource: DataSource<List<WordResponseDto>> = RemoteDateSource()
) : Repository<List<Word>> {
    override fun getData(searchingWord: String): Single<List<Word>> {
        return dataSource.getData(searchingWord = searchingWord).map { dtoList ->
            dtoList.map { dto ->
                dto.toWord()
            }
        }
    }
}