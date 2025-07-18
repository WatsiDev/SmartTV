package com.watsidev.producto3.model.data

import com.google.gson.annotations.SerializedName

data class MovieResponse(
    @SerializedName("results") val results: List<Movie>
)

data class Movie(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("release_date") val releaseDate: String,
    @SerializedName("vote_average") val voteAverage: Double,
    @SerializedName("vote_count") val voteCount: Int
) {
    fun fullPosterUrl() = "https://image.tmdb.org/t/p/w500$posterPath"
    fun fullBackdropUrl() = "https://image.tmdb.org/t/p/w780$backdropPath"
}
