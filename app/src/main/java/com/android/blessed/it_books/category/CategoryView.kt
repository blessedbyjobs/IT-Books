package com.android.blessed.it_books.category

import com.android.blessed.it_books.models.Book
import com.arellomobile.mvp.MvpView

interface CategoryView : MvpView {
    fun displayBooks(books : List<Book>)

    fun showProgressBar()
    fun hideProgressBar()

    fun showRequestError()
    fun showInternetError()
}