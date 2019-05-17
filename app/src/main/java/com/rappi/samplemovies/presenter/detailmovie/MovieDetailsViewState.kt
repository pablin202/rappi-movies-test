package com.rappi.samplemovies.presenter.detailmovie

import com.rappi.samplemovies.domain.models.VideoModel

data class MovieDetailsViewState(
    var isLoading: Boolean = true,
    var title: String? = null,
    var overview: String? = null,
    var videos: List<VideoModel>? = null,
    var homepage: String? = null,
    var releaseDate: String? = null,
    var votesAverage: Double? = null,
    var backdropUrl: String? = null,
    var genres: List<String>? = null
)