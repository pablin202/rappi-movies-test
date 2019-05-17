package com.rappi.samplemovies.usercases

import com.rappi.samplemovies.domain.common.Transformer
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable

open class  GetTopRatedMovies(transformer: Transformer<List<MovieModel>>,
                              private val moviesRepository: IMoviesRepository
) : UseCase<List<MovieModel>>(transformer) {
    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieModel>> {
        return moviesRepository.getTopRatedMovies()
    }

}