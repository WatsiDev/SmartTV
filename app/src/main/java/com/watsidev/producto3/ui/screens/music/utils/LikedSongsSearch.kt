package com.watsidev.producto3.ui.screens.music.utils

import com.watsidev.producto3.ui.screens.music.MusicLocalModel
import com.watsidev.producto3.ui.screens.music.MusicResourcesDataSource

fun getSongsForPlaylistOrFavorites(playlistId: String?): List<MusicLocalModel> {
    return if (playlistId != null) {
        // Busca el playlist por ID
        MusicResourcesDataSource.myPlaylists
            .find { it.id == playlistId }
            ?.musics ?: emptyList()
    } else {
        // Canciones favoritas (de todos los playlists)
        MusicResourcesDataSource.myPlaylists
            .flatMap { it.musics }
            .filter { it.isLiked }
    }
}
