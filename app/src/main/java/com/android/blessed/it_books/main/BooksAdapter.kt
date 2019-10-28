package com.android.blessed.it_books.main

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.android.blessed.it_books.R
import com.android.blessed.it_books.detail.DetailBookActivity
import com.android.blessed.it_books.models.Book
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import java.io.Serializable

class BooksAdapter(private val context: Context, var data: List<Book>?) :
    RecyclerView.Adapter<BooksAdapter.BookViewHolder>() {
    inner class BookViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val bookTitle: TextView = itemView.findViewById(R.id.book_title)
        val bookSubtitle: TextView = itemView.findViewById(R.id.book_subtitle)
        val bookImage: ImageView = itemView.findViewById(R.id.book_image)
        val bookPrice: TextView = itemView.findViewById(R.id.book_price)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.book_recycler_view_item, null)
        return BookViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val bookData = data!![position]

        Glide.with(context)
            .asDrawable()
            .load(bookData.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(holder.bookImage)
        holder.bookTitle.text = bookData.title
        holder.bookSubtitle.text = bookData.subtitle
        holder.bookPrice.text = bookData.price


        holder.itemView.setOnClickListener { view ->
            val i = Intent(view.context, DetailBookActivity::class.java)
            i.putExtra("BOOK_ISBN", bookData.isbn13)
            view.context.startActivity(i)
        }
    }

    override fun getItemCount(): Int {
        return data!!.size
    }


}
