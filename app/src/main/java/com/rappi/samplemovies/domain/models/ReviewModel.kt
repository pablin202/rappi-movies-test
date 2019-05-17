package com.rappi.samplemovies.domain.models

data class ReviewModel(
    var id: String,
    var author: String,
    var content: String? = null
)