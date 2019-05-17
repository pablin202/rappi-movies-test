package com.rappi.samplemovies.data.common

import com.rappi.samplemovies.data.entities.Details
import com.rappi.samplemovies.data.entities.Movie
import com.rappi.samplemovies.domain.models.*

class Converters {

    companion object {

        fun movieApiToMovieModel(source: Movie): MovieModel {
            return MovieModel(
                id = source.id,
                voteCount = source.voteCount,
                voteAverage = source.voteAverage,
                popularity = source.popularity,
                adult = source.adult,
                title = source.title,
                posterPath = source.posterPath,
                originalLanguage = source.originalLanguage,
                backdropPath = source.backdropPath,
                originalTitle = source.originalTitle,
                releaseDate = source.releaseDate,
                overview = source.overview
            )
        }


        fun movieModelListToMovieApiList(source: List<MovieModel>, type: Int = 0): List<Movie> {

            var returnValue = ArrayList<Movie>()

            source.forEach {
                returnValue.add(
                    Movie(
                        id = it.id,
                        voteCount = it.voteCount,
                        voteAverage = it.voteAverage,
                        popularity = it.popularity,
                        adult = it.adult,
                        title = it.title,
                        posterPath = it.posterPath,
                        originalLanguage = it.originalLanguage,
                        backdropPath = it.backdropPath,
                        originalTitle = it.originalTitle,
                        releaseDate = it.releaseDate,
                        overview = it.overview,
                        type = type
                    )
                )
            }

            return returnValue
        }

        fun movieApiToMovieModelList(source: List<Movie>): List<MovieModel> {

            var returnValue = ArrayList<MovieModel>()

            source.forEach {
                returnValue.add(
                    MovieModel(
                        id = it.id,
                        voteCount = it.voteCount,
                        voteAverage = it.voteAverage,
                        popularity = it.popularity,
                        adult = it.adult,
                        title = it.title,
                        posterPath = it.posterPath,
                        originalLanguage = it.originalLanguage,
                        backdropPath = it.backdropPath,
                        originalTitle = it.originalTitle,
                        releaseDate = it.releaseDate,
                        overview = it.overview
                    )
                )
            }

            return returnValue
        }

        fun detailsApiToMovieModel(source: Details): MovieModel {

            val movieModel = MovieModel(
                id = source.id,
                voteCount = source.voteCount,
                video = source.video,
                voteAverage = source.voteAverage,
                popularity = source.popularity,
                adult = source.adult,
                title = source.title,
                posterPath = source.posterPath,
                originalTitle = source.originalTitle,
                backdropPath = source.backdropPath,
                originalLanguage = source.originalLanguage,
                releaseDate = source.releaseDate,
                overview = source.overview
            )

            val details = MovieDetailsModel().apply {
                this.overview = source.overview
                this.budget = source.budget
                this.homepage = source.homepage
                this.imdbId = source.imdbId
                this.revenue = source.revenue
                this.runtime = source.runtime
                this.tagline = source.tagline
            }

            source.genres?.let {
                val genreModel = it.map { genreData ->
                    return@map GenreModel(genreData.id, genreData.name)
                }
                details.genres = genreModel
            }

            // Take only YouTube trailers
            source.videos?.let {
                val videosModel = it.results?.filter { videoData ->
                    videoData.site.equals(VideoModel.SOURCE_YOUTUBE) &&
                            videoData.type.equals(VideoModel.TYPE_TRAILER)
                }?.map { videoData ->
                    return@map VideoModel(
                        id = videoData.id,
                        name = videoData.name,
                        youtubeKey = videoData.key
                    )
                }
                details.videos = videosModel
            }

            source.reviews?.let {
                val reviewModel = it.results?.map { reviewData ->
                    return@map ReviewModel(
                        id = reviewData.id,
                        author = reviewData.author,
                        content = reviewData.content
                    )
                }

                details.reviews = reviewModel
            }
            movieModel.details = details
            return movieModel
        }
    }
}