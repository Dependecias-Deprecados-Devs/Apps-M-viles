package com.example.moviecompose.data.model

import android.icu.text.CaseMap.Title

data class MovieWrapper(
    @SerializedName("results")
    val movies: List<Movies>
)

data class Movies(
    val id: Int,
    val title: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("vote_average")
    val voteAverage: Double,

    var isFavorite: Boolean = false
)