package com.rappi.samplemovies.presenter.search

import com.rappi.samplemovies.domain.models.MovieModel

data class SearchViewState(
    val isLoading: Boolean = false,
    val movies: List<MovieModel>? = null,
    val lastSearchedQuery: String? = null,
    val showNoResultsMessage: Boolean = false
)