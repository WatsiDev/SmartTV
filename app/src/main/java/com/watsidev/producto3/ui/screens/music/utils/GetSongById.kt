package com.watsidev.producto3.ui.screens.music.utils

import com.watsidev.producto3.ui.screens.music.MusicLocalModel
import com.watsidev.producto3.ui.screens.music.MusicResourcesDataSource

fun getPlaylistFromSongId(songId: Int): Pair<List<MusicLocalModel>, Int>? {
    val playlist = MusicResourcesDataSource.myPlaylists
        .find { playlist ->
            playlist.musics.any { it.id == songId }
        } ?: return null

    val songs = playlist.musics
    val startIndex = songs.indexOfFirst { it.id == songId }

    return songs to startIndex
}
