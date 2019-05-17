package com.rappi.samplemovies.presenter.topratedmovies

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rappi.samplemovies.usercases.GetTopRatedMovies

class TopRatedMoviesVMFactory(private val useCase: GetTopRatedMovies) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return TopRatedMoviesViewModel(useCase) as T
    }

}