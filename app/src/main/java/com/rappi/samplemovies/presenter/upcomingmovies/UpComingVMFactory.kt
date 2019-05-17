package com.rappi.samplemovies.presenter.upcomingmovies

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.rappi.samplemovies.usercases.GetUpComingMovies


class UpComingVMFactory(private val useCase: GetUpComingMovies) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return UpComingMoviesViewModel(useCase) as T
    }

}