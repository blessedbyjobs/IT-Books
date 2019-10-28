package com.android.blessed.it_books.detail

import com.android.blessed.it_books.models.DetailBook
import com.arellomobile.mvp.MvpView

interface DetailBookView : MvpView {
    fun displayDetailBook(detailBook : DetailBook)

    fun showProgressBar()
    fun hideProgressBar()

    fun showRequestError()
    fun showInternetError()
}