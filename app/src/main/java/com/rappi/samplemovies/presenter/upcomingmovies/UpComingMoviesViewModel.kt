package com.rappi.samplemovies.presenter.upcomingmovies

import android.arch.lifecycle.MutableLiveData
import com.rappi.samplemovies.presenter.base.BaseViewModel
import com.rappi.samplemovies.presenter.base.SingleLiveEvent
import com.rappi.samplemovies.usercases.GetUpComingMovies
import com.rappi.samplemovies.presenter.base.GeneralMoviesViewState

class UpComingMoviesViewModel(private val getUpComingMovies: GetUpComingMovies) :
    BaseViewModel() {

    var viewState: MutableLiveData<GeneralMoviesViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> =
        SingleLiveEvent()

    init {
        viewState.value = GeneralMoviesViewState()
    }

    fun getUpComingMovies() {
        addDisposable(getUpComingMovies.observable()
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