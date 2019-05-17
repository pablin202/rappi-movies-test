package com.rappi.samplemovies.domain.interfaces

import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable


interface IMoviesCache {

    fun clearAll()
    fun clearPopulars()
    fun clearTopRated()
    fun clearUpComing()

    fun savePopulars(movie: List<MovieModel>)
    fun saveTopRated(movie: List<MovieModel>)
    fun saveUpComing(movie: List<MovieModel>)

    fun getPopularsMovies(): Observable<List<MovieModel>>
    fun getTopRatedMovies(): Observable<List<MovieModel>>
    fun getUpcomingMovies(): Observable<List<MovieModel>>

    fun isPopularEmpty(): Observable<Boolean>
    fun isTopRatedEmpty(): Observable<Boolean>
    fun isUpComingEmpty(): Observable<Boolean>

}