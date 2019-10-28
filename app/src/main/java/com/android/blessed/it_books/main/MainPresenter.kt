package com.android.blessed.it_books.main

import com.android.blessed.it_books.network.BooksRepositoryProvider
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

@InjectViewState
class MainPresenter : MvpPresenter<MainView>(), MainPresenterMVP {
    override fun loadHotReleases() {
        viewState.showProgressBar()
        val repository = BooksRepositoryProvider.provideBooksRepository()
        repository.getHotReleases()
            .doOnTerminate { viewState.hideProgressBar() }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result -> viewState.updateHotReleases(result.books)
            }, { error ->
                run {
                    if (error is IOException) {
                        viewState.showInternetError()
                    } else viewState.showRequestError()
                }
            })
    }
}