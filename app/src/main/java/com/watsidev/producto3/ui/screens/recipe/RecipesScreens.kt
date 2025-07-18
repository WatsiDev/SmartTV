package com.watsidev.producto3.ui.screens.recipe

import kotlinx.serialization.Serializable

@Serializable
data class VideoRecipe(
    val id: Int
)

@Serializable
object Favorites

@Serializable
data class Detail(
    val id: Int
)