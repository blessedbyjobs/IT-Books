package com.android.blessed.it_books.search

interface SearchPresenterMVP {
    fun getCategories()

    fun getSearchResults(query : String)
}