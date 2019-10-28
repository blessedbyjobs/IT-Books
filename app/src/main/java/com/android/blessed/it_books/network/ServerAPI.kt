package com.android.blessed.it_books.network

import com.android.blessed.it_books.models.DetailBook
import com.android.blessed.it_books.models.ServerResponse
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.converter.gson.GsonConverterFactory
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.Retrofit
import retrofit2.http.Path

interface ServerAPI {
    @GET(StringUtils.HOT_RELEASES)
    fun getHotReleases() : Observable<ServerResponse>

    @GET(StringUtils.BOOKS + "/{isbn}")
    fun getDetailBook(@Path("isbn") isbn : String) : Observable<DetailBook>

    @GET(StringUtils.SEARCH + "/{request}")
    fun getBooksByCategory(@Path("request") request : String) : Observable<ServerResponse>

    companion object Factory {
        fun create(): ServerAPI {
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://api.itbook.store/1.0/")
                .build()

            return retrofit.create(ServerAPI::class.java)
        }
    }
}