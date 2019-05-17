package com.rappi.samplemovies.usercases

import com.rappi.samplemovies.domain.common.Transformer
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable
import java.lang.IllegalArgumentException

class GetMovieDetails(
    transformer: Transformer<MovieModel>,
    private val moviesRepository: IMoviesRepository
) : UseCase<MovieModel>(transformer) {

    companion object {
        private const val PARAM_MOVIE_ENTITY = "param:movie"
    }

    fun getById(movieId: Int): Observable<MovieModel> {
        val data = HashMap<String, Int>()
        data[PARAM_MOVIE_ENTITY] = movieId
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<MovieModel> {
        val movieId = data?.get(PARAM_MOVIE_ENTITY)
        movieId?.let {
            return moviesRepository.getMovie(it as Int)
        } ?: return Observable.error { IllegalArgumentException("MovieId must be provided.") }
    }
}