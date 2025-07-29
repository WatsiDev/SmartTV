package com.watsidev.producto3.ui.screens.music

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import com.watsidev.producto3.R
import com.watsidev.producto3.ui.screens.music.MusicResourcesDataSource.myPlaylists

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PlaylistScreen(
    onClick: (Any) -> Unit,
    onClickPlaylist: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Image(
            painterResource(R.drawable.music_bg),
            contentDescription = "Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxSize()
        )
        Row(
            modifier = Modifier
                .fillMaxSize()
        ) {
            MusicSideNavBar(onClick = { onClick(it) })
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                item {
                    Text(
                        "Tus playlist",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier
                            .padding(vertical = 22.dp, horizontal = 16.dp)
                    )
                }
                items(myPlaylists) { playlist ->
                    PlaylistCard(playlistLocalModel = playlist, onPlaylistNavigate = { onClickPlaylist(it) })
                }
            }
        }
    }
}


@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun PlaylistCard(
    onPlaylistNavigate: (String) -> Unit,
    playlistLocalModel: PlaylistLocalModel
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        modifier = Modifier
            .clickable{ onPlaylistNavigate(playlistLocalModel.id) }
            .fillMaxWidth()
            .background(Color.Black.copy(alpha = 0.25f))
    ) {
        Image(
            painterResource(playlistLocalModel.cover),
            contentDescription = playlistLocalModel.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(80.dp)
                .aspectRatio(1f)
        )
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
            modifier = Modifier
                .height(80.dp)
                .weight(1f)
        ) {
            Text(
                playlistLocalModel.name,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                "WatsiDev"
            )
        }
        Icon(
            Icons.Outlined.Add,
            contentDescription = "Add song to playlist"
        )
        Spacer(Modifier.width(10.dp))
    }
}