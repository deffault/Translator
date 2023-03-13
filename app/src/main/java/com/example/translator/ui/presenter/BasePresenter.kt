package com.example.translator.ui.presenter

import com.example.translator.ui.view.BaseView

interface BasePresenter<V: BaseView> {
    fun attach(view: V)
    fun detach(view: V)
}