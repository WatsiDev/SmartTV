package com.watsidev.producto3.ui.screens.music

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.watsidev.producto3.R
import com.watsidev.producto3.model.data.music.SpotifyAlbum
import com.watsidev.producto3.model.data.music.SpotifyArtist
import com.watsidev.producto3.ui.screens.music.MusicResourcesDataSource.myPlaylists

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MusicScreen(
    onClick: (Any) -> Unit,
    onClickCover: (Any) -> Unit,
    viewModel: SpotifyViewModel = viewModel()
) {
    val token = viewModel.token
    val artists = viewModel.popularArtists
    val albums = viewModel.newAlbums
    val playlists = viewModel.featuredPlaylists

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painterResource(R.drawable.music_bg),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        Row(modifier = Modifier.fillMaxSize()) {
            MusicSideNavBar(onClick = { onClick(it) })
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Text(
                        "¿Qué vamos a escuchar hoy?",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Black,
                        modifier = Modifier.padding(vertical = 22.dp, horizontal = 16.dp)
                    )
                }
                // Sección playlists destacadas
                if (myPlaylists.isNotEmpty()) {
                    item {
                        PlaylistRow("Mis Playlists", myPlaylists, onClickCover = { onClickCover(it) })
                    }
                }
                // Sección artistas populares
                if (artists.isNotEmpty()) {
                    item {
                        ArtistRow("Artistas populares", artists, onClickCover = { onClickCover(it) })
                    }
                }
                // Sección álbumes nuevos
                if (albums.isNotEmpty()) {
                    item {
                        AlbumRow("Álbumes recientes", albums, onClickCover = { onClickCover(it) })
                    }
                }
            }
        }
    }
}

@Composable
fun AlbumRow(title: String, albums: List<SpotifyAlbum>, onClickCover: (Any) -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(albums) { album ->
                ArtistCard(
                    name = album.name,
                    imageUrl = album.images.firstOrNull()?.url,
                    onClickCover = { onClickCover(album.id) }
                )
            }
        }
    }
}

@Composable
fun PlaylistRow(title: String, playlists: List<PlaylistLocalModel>, onClickCover: (Any) -> Unit) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        LazyRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(playlists) { playlist ->
                ArtistCard(
                    name = playlist.name,
                    imageUrl = playlist.cover,
                    onClickCover = { onClickCover(playlist.id) }
                )
            }
        }
    }
}

@Composable
fun ArtistRow(
    title: String,
    artists: List<SpotifyArtist>,
    onClickCover: (Any) -> Unit
) {
    Column(modifier = Modifier.padding(8.dp)) {
        Text(title, fontWeight = FontWeight.Bold, fontSize = 20.sp)

        LazyRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            items(artists) { artist ->
                ArtistCard(
                    name = artist.name,
                    imageUrl = artist.images.firstOrNull()?.url,
                    onClickCover = { onClickCover(artist.id) }
                )
            }
        }
    }
}


@Composable
fun ArtistCard(
    name: String,
    imageUrl: Any?,
    onClickCover: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .clickable{ onClickCover() },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imageUrl,
            contentDescription = name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .width(125.dp)
                .aspectRatio(1f)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(name, maxLines = 1, overflow = TextOverflow.Ellipsis)
    }
}



@Composable
fun MusicSideNavBar(
    onClick: (Any) -> Unit,
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier
            .shadow(8.dp)
            .background(Color.Black.copy(alpha = 0.5f))
            .padding(horizontal = 25.dp, vertical = 10.dp)
            .fillMaxHeight()
            .width(175.dp),
    ) {
        Image(
            painterResource(R.drawable.music_logo),
            contentDescription = "Logo Music",
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp, horizontal = 10.dp)
        )
        musicRoutes.forEach { it ->
            ItemSideNavBar(
                itemNav = it,
                onClick = { onClick(it) },
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ItemSideNavBar(
    itemNav: MusicNavigation,
    onClick: (Any) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onClick(itemNav.route) }
            .height(70.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Icon(
            itemNav.icon,
            contentDescription = itemNav.title
        )
        Text(
            itemNav.title
        )
    }
}