package com.android.blessed.it_books.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Book(
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("subtitle")
    @Expose
    val subtitle: String,
    @SerializedName("isbn13")
    @Expose
    val isbn13: String,
    @SerializedName("price")
    @Expose
    val price: String,
    @SerializedName("image")
    @Expose
    val image: String,
    @SerializedName("url")
    @Expose
    val url: String
)

data class ServerResponse(
    @SerializedName("error")
    @Expose
    val error: String,
    @SerializedName("total")
    @Expose
    val total: String,
    @SerializedName("books")
    @Expose
    val books: List<Book>
)
