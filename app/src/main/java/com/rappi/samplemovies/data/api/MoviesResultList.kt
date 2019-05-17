package com.rappi.samplemovies.data.api

import com.google.gson.annotations.SerializedName
import com.rappi.samplemovies.data.entities.Movie

class MoviesResultList {
    var page: Int = 0
    @SerializedName("results")
    lateinit var movies: List<Movie>
}