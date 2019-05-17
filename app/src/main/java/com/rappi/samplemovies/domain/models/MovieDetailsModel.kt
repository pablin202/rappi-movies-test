package com.rappi.samplemovies.domain.models

data class MovieDetailsModel(
    var belongsToCollection: Any? = null,
    var budget: Int? = null,
    var homepage: String? = null,
    var imdbId: String? = null,
    var overview: String? = null,
    var revenue: Int? = null,
    var runtime: Int? = null,
    var status: String? = null,
    var tagline: String? = null,
    var reviews: List<ReviewModel>? = null,
    var videos: List<VideoModel>? = null,
    var genres: List<GenreModel>? = null
)