package com.android.blessed.it_books.detail

import android.util.Log
import com.android.blessed.it_books.network.BooksRepositoryProvider
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

@InjectViewState
class DetailBookPresenter : MvpPresenter<DetailBookView>(), DetailBookPresenterMVP {
    override fun loadDetailBook(isbn: String) {
        viewState.showProgressBar()
        val repository = BooksRepositoryProvider.provideBooksRepository()
        repository.getDetailBook(isbn)
            .doOnTerminate { viewState.hideProgressBar() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result -> viewState.displayDetailBook(result)
            }, { error ->
                run {
                    if (error is IOException) {
                        viewState.showInternetError()
                    } else viewState.showRequestError()
                }
            })
    }

}