package com.android.blessed.it_books.category

import android.util.Log
import com.android.blessed.it_books.network.BooksRepositoryProvider
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.io.IOException

@InjectViewState
class CategoryPresenter : MvpPresenter<CategoryView>(), CategoryPresenterMVP {
    override fun loadBooksByCategory(category: String) {
        val repository = BooksRepositoryProvider.provideBooksRepository()
        repository.getBooksByCategory(category)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result -> viewState.displayBooks(result.books)
            }, { error -> Log.i("Error", "error")
            })
    }

}