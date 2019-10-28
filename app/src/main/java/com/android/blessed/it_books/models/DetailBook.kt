package com.android.blessed.it_books.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class DetailBook (
    @SerializedName("title")
    @Expose
    val title: String,
    @SerializedName("subtitle")
    @Expose
    val subtitle: String,
    @SerializedName("authors")
    @Expose
    val authors: String,
    @SerializedName("publisher")
    @Expose
    val publisher: String,
    @SerializedName("language")
    @Expose
    val language: String,
    @SerializedName("year")
    @Expose
    val year: String,
    @SerializedName("desc")
    @Expose
    val desc: String,
    @SerializedName("price")
    @Expose
    val price: String,
    @SerializedName("image")
    @Expose
    val image: String
)