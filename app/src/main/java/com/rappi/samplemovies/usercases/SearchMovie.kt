package com.rappi.samplemovies.usercases

import com.rappi.samplemovies.domain.common.Transformer
import com.rappi.samplemovies.domain.interfaces.IMoviesRepository
import com.rappi.samplemovies.domain.models.MovieModel
import io.reactivex.Observable

class SearchMovie(transformer: Transformer<List<MovieModel>>,
                  private val moviesRepository: IMoviesRepository
) : UseCase<List<MovieModel>>(transformer) {

    companion object {
        private const val PARAM_SEARCH_QUERY = "param:search_query"
    }

    fun search(query: String): Observable<List<MovieModel>> {
        val data = HashMap<String, String>()
        data[PARAM_SEARCH_QUERY] = query
        return observable(data)
    }

    override fun createObservable(data: Map<String, Any>?): Observable<List<MovieModel>> {
        val query = data?.get(PARAM_SEARCH_QUERY)
        query?.let {
            return moviesRepository.search(it as String)
        } ?: return Observable.just(emptyList())
    }

}