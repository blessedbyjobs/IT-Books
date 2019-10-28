package com.android.blessed.it_books.network

import com.android.blessed.it_books.models.DetailBook
import com.android.blessed.it_books.models.ServerResponse
import io.reactivex.Observable

object BooksRepositoryProvider {
    fun provideBooksRepository(): BooksRepository {
        return BooksRepository(ServerAPI.create())
    }
}

class BooksRepository(private val apiService: ServerAPI) {
    fun getHotReleases(): Observable<ServerResponse> {
        return apiService.getHotReleases()
    }

    fun getDetailBook(isbn : String): Observable<DetailBook> {
        return apiService.getDetailBook(isbn)
    }

    fun getBooksByCategory(category : String) : Observable<ServerResponse> {
        return apiService.getBooksByCategory(category)
    }
}