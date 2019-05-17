package com.rappi.samplemovies.presenter.search

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.SearchView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.base.BaseFragment
import com.rappi.samplemovies.presenter.common.ImageLoader
import com.rappi.samplemovies.presenter.common.app
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_movies.*
import java.util.concurrent.TimeUnit
import javax.inject.Inject


class SearchFragment : BaseFragment() {

    @Inject
    lateinit var factory: SearchVMFactory
    @Inject
    lateinit var imageLoader: ImageLoader
    private lateinit var viewModel: SearchViewModel
    private lateinit var searchResultsAdapter: SearchResultsAdapter
    private lateinit var searchSubject: PublishSubject<String>
    private val compositeDisposable = CompositeDisposable()
    private var lastString = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.app!!.createSearchComponent().inject(this)
        viewModel = ViewModelProviders.of(this, factory).get(SearchViewModel::class.java)
        searchSubject = PublishSubject.create()

        val disposable = searchSubject.debounce(1, TimeUnit.SECONDS)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                if (it != searchResultsAdapter.query) {
                    viewModel.search(it)
                }
            }

        compositeDisposable.add(disposable)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.viewState.observe(this, Observer {
            if (it != null) handleViewState(it)
        })
        viewModel.errorState.observe(this, Observer { throwable ->
            throwable?.let {
                Toast.makeText(activity, throwable.message, Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun handleViewState(state: SearchViewState) {
        progress_bar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        val movies = state.movies ?: listOf()
        if (state.showNoResultsMessage) {
            search_movies_no_results_message.visibility = View.VISIBLE
            search_movies_no_results_message.text = String.format(
                getString(
                    R.string.search_no_results_message,
                    state.lastSearchedQuery
                )
            )
        } else {
            search_movies_no_results_message.visibility = View.GONE
        }
        searchResultsAdapter.setResults(movies, state.lastSearchedQuery)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        searchResultsAdapter = SearchResultsAdapter(imageLoader) { movie, movieView ->
            navigateToMovieDetailsActivity(movie, movieView)
        }

        recycler_view.layoutManager = GridLayoutManager(context, 3)
        recycler_view.adapter = searchResultsAdapter

        search_view.queryHint = context!!.getString(R.string.search).plus("...")
        search_view.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    lastString = it
                }
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                lastString = newText
                searchSubject.onNext(newText)
                return true
            }
        })
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("lastSearch", lastString)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        compositeDisposable.clear()
        activity?.app!!.releaseSearchComponent()
    }
}
