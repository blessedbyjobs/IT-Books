package com.android.blessed.it_books.search

import com.android.blessed.it_books.models.Book
import com.android.blessed.it_books.models.ProgrammingLanguage
import com.arellomobile.mvp.MvpView

interface SearchView : MvpView {
    fun updateCategories(categories : ArrayList<ProgrammingLanguage>)
    fun displayBooks(fresh_books : List<Book>)
}