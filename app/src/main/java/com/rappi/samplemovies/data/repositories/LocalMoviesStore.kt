package com.rappi.samplemovies.data.repositories

import com.rappi.samplemovies.domain.interfaces.IMoviesCache
import com.rappi.samplemovies.domain.interfaces.IMoviesStore
import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable

class LocalMoviesStore(private val moviesLocals: IMoviesCache) :
    IMoviesStore {

    override fun clearAll() {
        moviesLocals.clearAll()
    }

    override fun clearPopulars() {
        moviesLocals.clearPopulars()
    }

    override fun clearTopRated() {
        moviesLocals.clearTopRated()
    }

    override fun clearUpComing() {
        moviesLocals.clearUpComing()
    }

    override fun savePopulars(movie: List<MovieModel>) {
        moviesLocals.savePopulars(movie)
    }

    override fun saveTopRated(movie: List<MovieModel>) {
        moviesLocals.saveTopRated(movie)
    }

    override fun saveUpComing(movie: List<MovieModel>) {
        moviesLocals.saveUpComing(movie)
    }

    override fun isPopularEmpty(): Observable<Boolean> {
        return moviesLocals.isPopularEmpty()
    }

    override fun isTopRatedEmpty(): Observable<Boolean> {
        return moviesLocals.isTopRatedEmpty()
    }

    override fun isUpComingEmpty(): Observable<Boolean> {
        return moviesLocals.isUpComingEmpty()
    }

    override fun getPopularsMovies(): Observable<List<MovieModel>> {
        return moviesLocals.getPopularsMovies()
    }

    override fun getTopRatedMovies(): Observable<List<MovieModel>> {
        return moviesLocals.getTopRatedMovies()
    }

    override fun getUpcomingMovies(): Observable<List<MovieModel>> {
        return moviesLocals.getUpcomingMovies()
    }

    override fun getMovieById(movieId: Int): Observable<MovieModel> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun search(query: String): Observable<List<MovieModel>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}