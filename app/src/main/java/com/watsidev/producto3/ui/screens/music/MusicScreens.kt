package com.watsidev.producto3.ui.screens.music

import kotlinx.serialization.Serializable


@Serializable
object HomeMusic

@Serializable
data class MusicPlayer(
    val id: Int,
    val fromFavorites: Boolean = false
)

@Serializable
object SearchMusic

@Serializable
object Playlist

@Serializable
data class LikedSongs(
    val id: String?
)

@Serializable
object MyAccountMusic