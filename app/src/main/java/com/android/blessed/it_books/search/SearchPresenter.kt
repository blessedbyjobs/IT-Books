package com.android.blessed.it_books.search

import android.util.Log
import com.android.blessed.it_books.R
import com.android.blessed.it_books.models.Book
import com.android.blessed.it_books.models.ProgrammingLanguage
import com.android.blessed.it_books.network.BooksRepositoryProvider
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

@InjectViewState
class SearchPresenter : MvpPresenter<SearchView>(), SearchPresenterMVP {
    override fun getSearchResults(query: String) {
        val repository = BooksRepositoryProvider.provideBooksRepository()
        repository.getBooksByCategory(query)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribe ({
                    result -> viewState.displayBooks(result.books)
            }, { error -> Log.i("Error", "error")
            })
    }

    private var languages: ArrayList<ProgrammingLanguage> = ArrayList()
    private var books: ArrayList<Book> = ArrayList()

    init {
        setupLanguages()
    }

    private fun setupLanguages() {
        languages.add(ProgrammingLanguage("C", R.drawable.ic_lang_c))
        languages.add(ProgrammingLanguage("C++", R.drawable.ic_lang_cplusplus))
        languages.add(ProgrammingLanguage("C#", R.drawable.ic_lang_csharp))

        languages.add(ProgrammingLanguage("Swift", R.drawable.ic_lang_swift))
        languages.add(ProgrammingLanguage("Java", R.drawable.ic_lang_java))
        languages.add(ProgrammingLanguage("Kotlin", R.drawable.ic_lang_kotlin))

        languages.add(ProgrammingLanguage("Go", R.drawable.ic_lang_go))
        languages.add(ProgrammingLanguage("Python", R.drawable.ic_lang_python))
        languages.add(ProgrammingLanguage("Ruby", R.drawable.ic_lang_ruby))

        languages.add(ProgrammingLanguage("HTML", R.drawable.ic_lang_html5))
        languages.add(ProgrammingLanguage("CSS", R.drawable.ic_lang_css3))
        languages.add(ProgrammingLanguage("JavaScript", R.drawable.ic_lang_javascript))
        languages.add(ProgrammingLanguage("PHP", R.drawable.ic_lang_php))
        languages.add(ProgrammingLanguage("TypeScript", R.drawable.ic_lang_typescript))
        languages.add(ProgrammingLanguage("VueJS", R.drawable.ic_lang_vuejs))
    }

    override fun getCategories() {
        viewState.updateCategories(languages)
    }
}