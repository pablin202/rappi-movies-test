package com.rappi.samplemovies.data.localdb

import com.rappi.samplemovies.data.common.Converters
import com.rappi.samplemovies.domain.interfaces.IMoviesCache
import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable

class MoviesLocals(database: Database) : IMoviesCache {

    private val dao: MoviesDao = database.getMoviesDao()

    override fun clearAll() {
        dao.deleteAll()
    }

    override fun clearPopulars() {
        dao.deletePopulars()
    }

    override fun clearTopRated() {
        dao.deleteTopRates()
    }

    override fun savePopulars(movie: List<MovieModel>) {
        dao.saveMovies(Converters.movieModelListToMovieApiList(movie, 1))
    }

    override fun saveTopRated(movie: List<MovieModel>) {
        dao.saveMovies(Converters.movieModelListToMovieApiList(movie, 2))
    }

    override fun saveUpComing(movie: List<MovieModel>) {
        dao.saveMovies(Converters.movieModelListToMovieApiList(movie, 3))
    }

    override fun isPopularEmpty(): Observable<Boolean> {
        return Observable.fromCallable { dao.getPopulars().isEmpty() }
    }

    override fun isTopRatedEmpty(): Observable<Boolean> {
        return Observable.fromCallable { dao.getTopRated().isEmpty() }
    }

    override fun isUpComingEmpty(): Observable<Boolean> {
        return Observable.fromCallable { dao.getUpcoming().isEmpty() }
    }

    override fun getPopularsMovies(): Observable<List<MovieModel>> {
        return Observable.fromCallable { Converters.movieApiToMovieModelList(dao.getPopulars()) }
    }

    override fun getTopRatedMovies(): Observable<List<MovieModel>> {
        return Observable.fromCallable { Converters.movieApiToMovieModelList(dao.getTopRated()) }
    }

    override fun getUpcomingMovies(): Observable<List<MovieModel>> {
        return Observable.fromCallable { Converters.movieApiToMovieModelList(dao.getUpcoming()) }
    }

    override fun clearUpComing() {
        dao.deleteUpcoming()
    }


}