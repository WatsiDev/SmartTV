package com.watsidev.producto3.ui.screens.home

import com.watsidev.producto3.model.data.Movie
import kotlinx.serialization.Serializable
import retrofit2.http.Query

@Serializable
data class DetailStreaming(
    val id: Int
)

@Serializable
data class SearchMovies(
    val query: String
)