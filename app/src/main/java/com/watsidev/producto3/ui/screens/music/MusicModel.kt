package com.watsidev.producto3.ui.screens.music

import kotlin.time.Duration

data class MusicLocalModel(
    val id: Int,
    val cover: Int,
    val title: String,
    val artist: String,
    val duration: String,
    var isLiked: Boolean,
    val songRes: Int
)

data class PlaylistLocalModel(
    val id: String,
    val name: String,
    val musics: List<MusicLocalModel>,
    val cover: Int
)