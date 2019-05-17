package com.rappi.samplemovies.presenter.search

import android.arch.lifecycle.MutableLiveData
import com.rappi.samplemovies.presenter.base.BaseViewModel
import com.rappi.samplemovies.presenter.base.SingleLiveEvent
import com.rappi.samplemovies.usercases.SearchMovie

class SearchViewModel(private val searchMovie: SearchMovie) : BaseViewModel() {

    var viewState: MutableLiveData<SearchViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> =
        SingleLiveEvent()

    init {
        viewState.value = SearchViewState()
    }

    fun search(query: String) {

        errorState.value = null

        if (query.isEmpty()) {
            viewState.value = viewState.value?.copy(
                isLoading = false,
                showNoResultsMessage = false,
                lastSearchedQuery = query,
                movies = null)
        } else {
            viewState.value = viewState.value?.copy(
                isLoading = true,
                showNoResultsMessage = false)

            performSearch(query)
        }
    }

    private fun performSearch(query: String) {

        addDisposable(searchMovie.search(query)
            .subscribe({ movies ->
                viewState.value = viewState.value?.copy(
                    isLoading = false,
                    movies = movies,
                    lastSearchedQuery = query,
                    showNoResultsMessage = movies.isEmpty())

            }, {
                viewState.value = viewState.value?.copy(
                    isLoading = false,
                    movies = null,
                    lastSearchedQuery = query
                )
                errorState.value = it
            }))
    }
}