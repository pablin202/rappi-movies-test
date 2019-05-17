package com.rappi.samplemovies.presenter.popularmovies

import android.arch.lifecycle.MutableLiveData
import com.rappi.samplemovies.presenter.base.BaseViewModel
import com.rappi.samplemovies.presenter.base.SingleLiveEvent
import com.rappi.samplemovies.usercases.GetPopularMovies
import com.rappi.samplemovies.presenter.base.GeneralMoviesViewState


class PopularMoviesViewModel(private val getPopularMovies: GetPopularMovies) :
    BaseViewModel() {

    var viewState: MutableLiveData<GeneralMoviesViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> =
        SingleLiveEvent()

    init {
        viewState.value = GeneralMoviesViewState()
    }

    fun getPopularMovies() {
        addDisposable(getPopularMovies.observable()
            .subscribe({ movies ->
                viewState.value?.let {
                    val newState = this.viewState.value?.copy(showLoading = false, movies = movies)
                    this.viewState.value = newState
                    this.errorState.value = null
                }

            }, {
                viewState.value = viewState.value?.copy(showLoading = false)
                errorState.value = it
            }))
    }
}