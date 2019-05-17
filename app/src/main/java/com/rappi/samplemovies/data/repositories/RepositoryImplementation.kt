package com.rappi.samplemovies.data.repositories

import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable

class RepositoryImplementation (private val localMoviesStore: LocalMoviesStore,
                                private val remoteDataStore: RemoteLocalStore) :
    IMoviesRepository {

    override fun getPopularMovies(): Observable<List<MovieModel>> {

        return localMoviesStore.isPopularEmpty().flatMap { empty ->
            if (!empty) {
                return@flatMap localMoviesStore.getPopularsMovies()
            }
            else {
                return@flatMap remoteDataStore.getPopularsMovies()
                    .doOnNext { movies ->
                        localMoviesStore.savePopulars(movies)
                    }
            }
        }
    }

    override fun getTopRatedMovies(): Observable<List<MovieModel>> {

        return localMoviesStore.isTopRatedEmpty().flatMap { empty ->
            if (!empty) {
                return@flatMap localMoviesStore.getTopRatedMovies()
            }
            else {
                return@flatMap remoteDataStore.getTopRatedMovies()
                    .doOnNext { movies ->
                        localMoviesStore.saveTopRated(movies)
                    }
            }
        }
    }

    override fun getUpComingMovies(): Observable<List<MovieModel>> {

        return localMoviesStore.isUpComingEmpty().flatMap { empty ->
            if (!empty) {
                return@flatMap localMoviesStore.getUpcomingMovies()
            }
            else {
                return@flatMap remoteDataStore.getUpcomingMovies()
                    .doOnNext { movies ->
                        localMoviesStore.saveUpComing(movies)
                    }
            }
        }
    }

    override fun search(query: String): Observable<List<MovieModel>> {
        return remoteDataStore.search(query)
    }

    override fun getMovie(movieId: Int): Observable<MovieModel> {
        return remoteDataStore.getMovieById(movieId)
    }

}