package com.android.blessed.it_books.main

import androidx.recyclerview.widget.DiffUtil
import com.android.blessed.it_books.models.Book

class BooksDiffUtilCallback(private val oldBooksList: List<Book>, private val newBooksList: List<Book>) :
    DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldBooksList.size
    }

    override fun getNewListSize(): Int {
        return newBooksList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBook = oldBooksList[oldItemPosition]
        val newBook = newBooksList[newItemPosition]
        return oldBook.isbn13 === newBook.isbn13
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldBook = oldBooksList[oldItemPosition]
        val newBook = newBooksList[newItemPosition]
        return oldBook.title == newBook.title &&
                oldBook.subtitle == newBook.subtitle &&
                oldBook.isbn13 == newBook.isbn13 &&
                oldBook.price == newBook.price &&
                oldBook.image == newBook.image &&
                oldBook.url == newBook.url
    }
}