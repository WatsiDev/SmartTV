package com.watsidev.producto3.ui.screens.music

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.watsidev.producto3.R

@Composable
fun PlayerFullScreen(
    idSong: Int,
    viewModel: PlayerViewModel,
    fromFavorites: Boolean = false // <-- parámetro nuevo
) {
    // Cuando entras a esta pantalla, reproducimos la canción nueva
    LaunchedEffect(idSong, fromFavorites) {
        viewModel.playSong(idSong, fromFavorites) // <-- se pasa el flag
    }

    val currentSong by viewModel.currentSong.collectAsState()
    val isPlaying by viewModel.isPlaying.collectAsState()
    val progress by viewModel.progress.collectAsState()
    val currentTime by viewModel.currentTime.collectAsState()
    val totalTime by viewModel.totalTime.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(R.drawable.player_bg),
            contentDescription = "Music Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            currentSong?.let { song ->
                TrackCover(song = song, modifier = Modifier.weight(1f))

                PlayerControls(
                    isPlaying = isPlaying,
                    isLiked = song.isLiked,
                    currentTime = currentTime,
                    totalTime = totalTime,
                    progress = progress,
                    onPlayPause = { viewModel.playPause() },
                    onNext = { viewModel.next() },
                    onPrevious = { viewModel.previous() },
                    onToggleLike = { viewModel.toggleFavorite() }
                )
            }
        }
    }
}





@Composable
fun TrackCover(
    song: MusicLocalModel,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(18.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Now playing")
        Image(
            painterResource(song.cover),
            contentDescription = song.title,
            modifier = Modifier
                .size(255.dp)
                .shadow(8.dp)
        )
        Text(
            song.title,
            fontWeight = FontWeight.SemiBold,
            fontSize = 18.sp
        )
        Text(
            song.artist,
            fontWeight = FontWeight.Light,
            fontSize = 14.sp
        )
    }
}