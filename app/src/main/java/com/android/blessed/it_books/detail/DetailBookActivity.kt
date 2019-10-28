package com.android.blessed.it_books.detail

import android.os.Bundle
import android.view.View
import com.android.blessed.it_books.CustomSnackBar
import com.android.blessed.it_books.R
import com.android.blessed.it_books.models.DetailBook
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.detail_book.*

class DetailBookActivity : MvpAppCompatActivity(), DetailBookView {
    @InjectPresenter
    lateinit var detailBookPresenter : DetailBookPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detail_book)

        go_back_button.setOnClickListener { finish() }
        detailBookPresenter.loadDetailBook(intent.extras!!.getString("BOOK_ISBN") as String)
    }

    override fun displayDetailBook(detailBook: DetailBook) {
        detail_book_title.text = detailBook.title
        detail_book_subtitle.text = detailBook.subtitle
        detail_book_author.text = detailBook.authors
        detail_book_publisher.text = detailBook.publisher
        detail_book_language.text = detailBook.language
        detail_book_description.text = detailBook.desc
        detail_book_price.text = detailBook.price
        detail_book_year.text = detailBook.year

        Glide.with(this)
            .asDrawable()
            .load(detailBook.image)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(detail_book_image)
    }

    override fun showProgressBar() {
        progress_bar_detail_book.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        progress_bar_detail_book.visibility = View.INVISIBLE
    }

    override fun showRequestError() {
        swipe_to_refresh.isRefreshing = false
        setUpSnackBar(resources.getString(R.string.hot_releases_error_text)).show()
    }

    override fun showInternetError() {
        swipe_to_refresh.isRefreshing = false
        setUpSnackBar(resources.getString(R.string.internet_error_text)).show()
    }

    private fun setUpSnackBar(text: String): Snackbar {
        return CustomSnackBar(
            Snackbar.make(
                findViewById(android.R.id.content),
                text, Snackbar.LENGTH_LONG
            ), this
        ).snackbar
    }
}
