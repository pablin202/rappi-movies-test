package com.rappi.samplemovies.presenter.popularmovies

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rappi.samplemovies.usercases.GetPopularMovies

class PopularMoviesVMFactory(private val useCase: GetPopularMovies) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PopularMoviesViewModel(useCase) as T
    }

}