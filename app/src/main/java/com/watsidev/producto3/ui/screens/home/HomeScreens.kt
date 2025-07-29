package com.watsidev.producto3.ui.screens.home

import kotlinx.serialization.Serializable

@Serializable
data class DetailStreaming(
    val id: Int
)

@Serializable
data class SearchMovies(
    val query: String
)