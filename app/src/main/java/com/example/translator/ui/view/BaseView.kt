package com.example.translator.ui.view

import com.example.translator.AppState

interface BaseView {
    fun renderData(state: AppState)
}