package com.android.blessed.it_books.search

import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.android.blessed.it_books.R
import com.android.blessed.it_books.main.BooksAdapter
import com.android.blessed.it_books.main.BooksDiffUtilCallback
import com.android.blessed.it_books.models.Book
import com.android.blessed.it_books.models.ProgrammingLanguage
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.books_search.*
import kotlinx.android.synthetic.main.books_search.go_back_button

class SearchActivity : MvpAppCompatActivity(), SearchView {
    @InjectPresenter
    lateinit var searchPresenter : SearchPresenter

    private lateinit var booksAdapter: BooksAdapter
    private var books: ArrayList<Book> = ArrayList()

    private lateinit var categoryAdapter: CategoryAdapter
    private var categories: ArrayList<ProgrammingLanguage> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.books_search)

        setToolbar()
        searchPresenter.getCategories()
        setRecyclerView()

        go_back_button.setOnClickListener { finish() }
        search_edit_text.addTextChangedListener {
            if (search_edit_text.text.isEmpty()) {
                search_text.text = "Categories"
                searchPresenter.getCategories()
            } else searchPresenter.getSearchResults(search_edit_text.text.toString())
        }
    }

    private fun setToolbar() {
        setSupportActionBar(search_toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun setRecyclerView() {
        books_categories_recycler_view.setHasFixedSize(true)

        books_categories_recycler_view.layoutManager = GridLayoutManager(this, 3)

        categoryAdapter = CategoryAdapter(this, categories)
        booksAdapter = BooksAdapter(this, books)
        books_categories_recycler_view.adapter = categoryAdapter
    }

    override fun displayBooks(fresh_books: List<Book>) {
        search_text.text = "Search Results"
        books_categories_recycler_view.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)

        booksAdapter = BooksAdapter(this, fresh_books)
        books_categories_recycler_view.adapter = booksAdapter

        val booksDiffUtilCallback = BooksDiffUtilCallback(booksAdapter.data!!, fresh_books)
        val booksDiffResult = DiffUtil.calculateDiff(booksDiffUtilCallback)
        booksAdapter.data = fresh_books
        booksDiffResult.dispatchUpdatesTo(booksAdapter)
    }

    override fun updateCategories(categories: ArrayList<ProgrammingLanguage>) {
        categoryAdapter.data = categories

        books_categories_recycler_view.layoutManager = GridLayoutManager(this, 3)

        categoryAdapter = CategoryAdapter(this, categories)
        books_categories_recycler_view.adapter = categoryAdapter
    }
}
