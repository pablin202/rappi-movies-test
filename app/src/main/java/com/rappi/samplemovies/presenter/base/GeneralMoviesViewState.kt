package com.rappi.samplemovies.presenter.base

import com.rappi.samplemovies.domain.models.MovieModel

data class GeneralMoviesViewState(
    var showLoading: Boolean = true,
    var movies: List<MovieModel>? = null
)