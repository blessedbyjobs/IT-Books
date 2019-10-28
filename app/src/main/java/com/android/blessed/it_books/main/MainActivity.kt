package com.android.blessed.it_books.main

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.blessed.it_books.CustomSnackBar
import com.android.blessed.it_books.R
import com.android.blessed.it_books.models.Book
import com.android.blessed.it_books.search.SearchActivity
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : MvpAppCompatActivity(), MainView {
    @InjectPresenter
    lateinit var mainPresenter : MainPresenter

    private lateinit var booksAdapter: BooksAdapter
    private var books: ArrayList<Book> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // настройка тулбара
        setToolbar()

        mainPresenter.loadHotReleases()

        // настройка RecyclerView
        setRecyclerView()

        swipe_to_refresh.setOnRefreshListener {
            mainPresenter.loadHotReleases()
        }

        go_to_categories_button.setOnClickListener {
            val i = Intent(this, SearchActivity::class.java)
            this.startActivity(i)
        }
    }

    private fun setToolbar() {
        setSupportActionBar(main_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setRecyclerView() {
        books_recycler_view.setHasFixedSize(true)

        val layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        books_recycler_view.layoutManager = layoutManager

        booksAdapter = BooksAdapter(this, books)
        books_recycler_view.adapter = booksAdapter
    }

    override fun updateHotReleases(books: List<Book>) {
        val booksDiffUtilCallback = BooksDiffUtilCallback(booksAdapter.data!!, books)
        val booksDiffResult = DiffUtil.calculateDiff(booksDiffUtilCallback)
        booksAdapter.data = books
        booksDiffResult.dispatchUpdatesTo(booksAdapter)
        swipe_to_refresh.isRefreshing = false
    }

    override fun showRequestError() {
        swipe_to_refresh.isRefreshing = false
        setUpSnackBar(resources.getString(R.string.hot_releases_error_text)).show()
    }

    override fun showProgressBar() {
        main_progress_bar_container.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        main_progress_bar_container.visibility = View.INVISIBLE
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
