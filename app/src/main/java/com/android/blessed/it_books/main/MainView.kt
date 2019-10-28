package com.android.blessed.it_books.main

import com.android.blessed.it_books.models.Book
import com.arellomobile.mvp.MvpView

interface MainView : MvpView {
    fun updateHotReleases(books : List<Book>)

    fun showProgressBar()
    fun hideProgressBar()

    fun showRequestError()
    fun showInternetError()
}