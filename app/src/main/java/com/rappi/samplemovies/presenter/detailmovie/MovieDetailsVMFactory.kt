package com.rappi.samplemovies.presenter.detailmovie

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rappi.samplemovies.usercases.GetMovieDetails

class MovieDetailsVMFactory(
    private val getMovieDetails: GetMovieDetails
) : ViewModelProvider.Factory {

    var movieId: Int = -1

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return MovieDetailsViewModel(
            getMovieDetails,
            movieId) as T
    }

}