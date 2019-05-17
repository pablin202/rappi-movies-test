package com.rappi.samplemovies.presenter.base

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.SearchView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.rappi.samplemovies.R
import com.rappi.samplemovies.presenter.common.ImageLoader
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject

open class BaseFragmentLocalMovies : BaseFragment() {

    @Inject
    lateinit var imageLoader: ImageLoader

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return layoutInflater.inflate(R.layout.fragment_movies, container, false)
    }

    protected val moviesAdapter by lazy {
        LocalMoviesAdapter(imageLoader) { movie, view ->
            navigateToMovieDetailsActivity(movie, view)
        }
    }

    protected fun handleViewState(state: GeneralMoviesViewState) {
        progress_bar.visibility = if (state.showLoading) View.VISIBLE else View.GONE
        state.movies?.let { moviesAdapter.addMovies(it) }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(recycler_view, search_view)
    }

    private fun initViews(recyclerView: RecyclerView, searchView: SearchView) {

        recyclerView.layoutManager = GridLayoutManager(context, 3)
        recyclerView.adapter = moviesAdapter

        searchView.queryHint = context!!.getString(R.string.search).plus("...")
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                moviesAdapter.filter.filter(query)
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                moviesAdapter.filter.filter(newText)
                return true
            }
        })
    }

}