package com.example.translator

import com.example.translator.domain.entity.Word

sealed class AppState {
    data class Success(val data: List<Word>): AppState()
    data class Error(val error: Throwable): AppState()
    object Loading: AppState()
}
