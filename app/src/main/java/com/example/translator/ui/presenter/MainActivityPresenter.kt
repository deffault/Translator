package com.example.translator.ui.presenter

import com.example.translator.AppState
import com.example.translator.data.RepositoryImpl
import com.example.translator.domain.interactor.BaseInteractor
import com.example.translator.domain.interactor.MainInteractor
import com.example.translator.ui.view.BaseView
import com.example.translator.ui.view.MainActivity
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers

class MainActivityPresenter(
    private val interactor: BaseInteractor<AppState> = MainInteractor(repository = RepositoryImpl()),
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),

) : BasePresenter<MainActivity> {
    private var currentView: BaseView? = null

    override fun attach(view: MainActivity) {
        if (currentView != view) {
            currentView = view
        }
    }

    override fun detach(view: MainActivity) {
        compositeDisposable.clear()
        if (currentView == view) {
            currentView = null
        }
    }

    fun getData(searchingWord: String) {
        compositeDisposable.add(
            interactor.getData(searchingWord = searchingWord)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe {
                    currentView?.renderData(AppState.Loading)
                }
                .subscribeBy(
                    onError = { throwable ->
                        currentView?.renderData(
                            state = AppState.Error(error = throwable)
                        )
                    },
                    onSuccess = { state ->
                        currentView?.renderData(
                            state = state
                        )
                    }
                )
        )
    }
}