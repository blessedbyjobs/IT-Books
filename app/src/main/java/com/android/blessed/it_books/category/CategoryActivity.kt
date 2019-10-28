package com.android.blessed.it_books.category

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.blessed.it_books.CustomSnackBar
import com.android.blessed.it_books.R
import com.android.blessed.it_books.main.BooksAdapter
import com.android.blessed.it_books.main.BooksDiffUtilCallback
import com.android.blessed.it_books.models.Book
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.books_category.*

class CategoryActivity : MvpAppCompatActivity(), CategoryView {
    @InjectPresenter
    lateinit var categoryPresenter : CategoryPresenter

    private var category : String? = null

    private lateinit var booksAdapter: BooksAdapter
    private var books: ArrayList<Book> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.books_category)

        category = intent.extras!!.getString("NAME")
        category_title.text = category

        go_back_button.setOnClickListener { finish() }

        categoryPresenter.loadBooksByCategory(category as String)
        setRecyclerView()
    }

    private fun setRecyclerView() {
        books_category_recycler_view.setHasFixedSize(true)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        books_category_recycler_view.layoutManager = layoutManager

        booksAdapter = BooksAdapter(this, books)
        books_category_recycler_view.adapter = booksAdapter
    }

    override fun displayBooks(books: List<Book>) {
        swipe_to_refresh.isRefreshing = false
        val booksDiffUtilCallback = BooksDiffUtilCallback(booksAdapter.data!!, books)
        val booksDiffResult = DiffUtil.calculateDiff(booksDiffUtilCallback)
        booksAdapter.data = books
        booksDiffResult.dispatchUpdatesTo(booksAdapter)
    }

    override fun showProgressBar() {
        category_progress_bar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        category_progress_bar.visibility = View.INVISIBLE
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
