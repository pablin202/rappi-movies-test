package com.rappi.samplemovies.data.api

import com.rappi.samplemovies.data.entities.Details
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    @GET("movie/{id}?append_to_response=videos,reviews")
    fun getMovieDetails(@Path("id") movieId: Int): Observable<Details>

    @GET("movie/popular")
    fun getPopularMovies(): Observable<MoviesResultList>

    @GET("movie/top_rated")
    fun getTopRatedMovies(): Observable<MoviesResultList>

    @GET("movie/upcoming")
    fun getUpcomingMovies(): Observable<MoviesResultList>

    @GET("search/movie")
    fun searchMovies(@Query("query") query: String): Observable<MoviesResultList>

}