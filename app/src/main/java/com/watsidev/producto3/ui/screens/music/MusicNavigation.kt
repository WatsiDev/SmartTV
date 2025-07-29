package com.watsidev.producto3.ui.screens.music

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.PlaylistPlay
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.PlaylistPlay
import androidx.compose.material.icons.outlined.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.watsidev.producto3.ui.navigation.Music
import kotlinx.serialization.Serializable

data class MusicNavigation(
    val id: Int,
    val title: String,
    val icon: ImageVector,
    val route: Any
)

val musicRoutes = listOf(
    MusicNavigation(
        id = 1,
        title = "Home",
        icon = Icons.Outlined.Home,
        route = Music
    ),
    MusicNavigation(
        id = 2,
        title = "Search",
        icon = Icons.Outlined.Search,
        route = SearchMusic
//        route = MusicPlayer
    ),
    MusicNavigation(
        id = 3,
        title = "Playlist",
        icon = Icons.AutoMirrored.Outlined.PlaylistPlay,
        route = Playlist
    ),
    MusicNavigation(
        id = 4,
        title = "Liked",
        icon = Icons.Outlined.FavoriteBorder,
        route = LikedSongs(null)
    ),
    MusicNavigation(
        id = 5,
        title = "My Account",
        icon = Icons.Outlined.AccountCircle,
        route = MyAccountMusic
    ),
)