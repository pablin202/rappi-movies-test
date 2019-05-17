package com.rappi.samplemovies.presenter.topratedmovies

import android.arch.lifecycle.MutableLiveData
import com.rappi.samplemovies.presenter.base.BaseViewModel
import com.rappi.samplemovies.presenter.base.SingleLiveEvent
import com.rappi.samplemovies.usercases.GetTopRatedMovies
import com.rappi.samplemovies.presenter.base.GeneralMoviesViewState

class TopRatedMoviesViewModel(private val getTopRatedMovies: GetTopRatedMovies) :
    BaseViewModel() {

    var viewState: MutableLiveData<GeneralMoviesViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable?> =
        SingleLiveEvent()

    init {
        viewState.value = GeneralMoviesViewState()
    }

    fun getTopRatedMovies() {
        addDisposable(getTopRatedMovies.observable()
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