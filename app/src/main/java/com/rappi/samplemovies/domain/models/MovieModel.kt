package com.rappi.samplemovies.domain.models

data class MovieModel(

    var id: Int = 0,
    var voteCount: Int = 0,
    var video: Boolean = false,
    var voteAverage: Double = 0.0,
    var popularity: Double = 0.0,
    var adult: Boolean = false,
    var details: MovieDetailsModel? = null,
    var title: String,
    var posterPath: String?,
    var originalLanguage: String,
    var originalTitle: String,
    var backdropPath: String?,
    var releaseDate: String,
    var overview: String? = null
) {
    companion object {
        private const val posterBaseUrl = "https://image.tmdb.org/t/p/w342"
        private const val backdropBaseUrl = "https://image.tmdb.org/t/p/w780"
        private const val youTubeBaseUrl = "https://www.youtube.com/watch?v="
    }

    val urlPoster
        get() = posterBaseUrl + posterPath

    val urlBackDropPath
        get() = backdropBaseUrl + backdropPath
}