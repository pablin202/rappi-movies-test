package com.rappi.samplemovies.presenter.detailmovie

import android.arch.lifecycle.MutableLiveData
import com.rappi.samplemovies.presenter.base.BaseViewModel
import com.rappi.samplemovies.presenter.base.SingleLiveEvent
import com.rappi.samplemovies.usercases.GetMovieDetails
import com.rappi.samplemovies.domain.models.MovieModel

class MovieDetailsViewModel(
    private val getMovieDetails: GetMovieDetails,
    private val movieId: Int
) : BaseViewModel() {

    lateinit var movie: MovieModel
    var viewState: MutableLiveData<MovieDetailsViewState> = MutableLiveData()
    var errorState: SingleLiveEvent<Throwable> =
        SingleLiveEvent()

    init {
        viewState.value = MovieDetailsViewState(isLoading = true)
    }

    fun getMovieDetails() {
        addDisposable(
            getMovieDetails.getById(movieId)
                .map {
                    movie = it
                }
                .subscribe(
                    { onMovieDetailsReceived(movie) },
                    { errorState.value = it }
                )
        )
    }


    private fun onMovieDetailsReceived(movie: MovieModel) {

        val newViewState = viewState.value?.copy(
            isLoading = false,
            title = movie.originalTitle,
            videos = movie.details?.videos,
            homepage = movie.details?.homepage,
            overview = movie.details?.overview,
            releaseDate = movie.releaseDate,
            votesAverage = movie.voteAverage,
            backdropUrl = movie.backdropPath,
            genres = arrayListOf()
        )

        viewState.value = newViewState

    }

    //movie.details?.genres

}