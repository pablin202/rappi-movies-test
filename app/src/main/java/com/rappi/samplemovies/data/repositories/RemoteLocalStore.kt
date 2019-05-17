package com.rappi.samplemovies.data.repositories

import com.rappi.samplemovies.data.api.Api
import com.rappi.samplemovies.data.common.Converters
import com.rappi.samplemovies.domain.interfaces.IMoviesStore
import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable

class RemoteLocalStore (private val api: Api): IMoviesStore {

    override fun getPopularsMovies(): Observable<List<MovieModel>> {
        return api.getPopularMovies().map { results ->
            results.movies.map { Converters.movieApiToMovieModel(it) }
        }
    }

    override fun getTopRatedMovies(): Observable<List<MovieModel>> {
        return api.getTopRatedMovies().map { results ->
            results.movies.map { Converters.movieApiToMovieModel(it) }
        }
    }

    override fun getUpcomingMovies(): Observable<List<MovieModel>> {
        return api.getUpcomingMovies().map { results ->
            results.movies.map { Converters.movieApiToMovieModel(it) }
        }
    }

    override fun getMovieById(movieId: Int): Observable<MovieModel> {
        return api.getMovieDetails(movieId).flatMap { detailedData ->
            Observable.just(Converters.detailsApiToMovieModel(detailedData))
        }
    }

    // Search
    override fun search(query: String): Observable<List<MovieModel>> {
        return api.searchMovies(query).map { results ->
            results.movies.map { Converters.movieApiToMovieModel(it) }
        }
    }

    override fun clearAll() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearPopulars() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearTopRated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearUpComing() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun savePopulars(movie: List<MovieModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveTopRated(movie: List<MovieModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun saveUpComing(movie: List<MovieModel>) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isPopularEmpty(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isTopRatedEmpty(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun isUpComingEmpty(): Observable<Boolean> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}