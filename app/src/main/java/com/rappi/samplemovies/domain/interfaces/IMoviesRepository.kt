package com.rappi.samplemovies.domain.interfaces

import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable

interface IMoviesRepository {

    fun getPopularMovies(): Observable<List<MovieModel>>
    fun getTopRatedMovies(): Observable<List<MovieModel>>
    fun getUpComingMovies(): Observable<List<MovieModel>>

    fun search(query: String): Observable<List<MovieModel>>

    fun getMovie(movieId: Int): Observable<MovieModel>
}