package com.watsidev.producto3.ui.screens.music

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Text
import com.watsidev.producto3.R
import com.watsidev.producto3.ui.screens.music.utils.getSongsForPlaylistOrFavorites

@Composable
fun LikedScreen(
    idPlaylist: String?,
    onClick: (Any) -> Unit,
    onPlayMusic: (Int) -> Unit
) {
    val songs = remember { getSongsForPlaylistOrFavorites(idPlaylist) }

    Box(modifier = Modifier.fillMaxSize()) {
        // Fondo
        Image(
            painterResource(R.drawable.music_bg),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )

        Row(modifier = Modifier.fillMaxSize()) {
            MusicSideNavBar(onClick = { onClick(it) })

            if (songs.isEmpty()) {
                // Mensaje de lista vacía
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = if (idPlaylist == null)
                            "No tienes canciones favoritas aún"
                        else
                            "Este playlist está vacío",
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            } else {
                // Lista de canciones
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(8.dp),
                    contentPadding = PaddingValues(8.dp)
                ) {
                    item {
                        Text(
                            if (idPlaylist == null) "Tus canciones favoritas!" else "Playlist",
                            fontSize = 35.sp,
                            fontWeight = FontWeight.Black,
                            modifier = Modifier.padding(vertical = 22.dp, horizontal = 16.dp)
                        )
                    }

                    itemsIndexed(songs) { index, song ->
                        SongCard(
                            position = index + 1,
                            song = song,
                            onPlayMusic = { onPlayMusic(it) }
                        )
                    }
                }
            }
        }
    }
}



@Composable
fun SongCard(
    position: Int,
    song: MusicLocalModel,
    onPlayMusic: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable{ onPlayMusic(song.id) }
            .background(Color.Black.copy(alpha = 0.25f))
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Spacer(Modifier.width(8.dp))
        Text(position.toString())

        Image(
            painterResource(song.cover),
            contentDescription = song.title,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(1f)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                song.title,
                fontSize = 16.sp
            )
            Text(
                song.artist,
                fontSize = 12.sp,
                fontWeight = FontWeight.Light
            )
        }

        Text(song.duration)
        Spacer(Modifier.width(8.dp))
    }
}