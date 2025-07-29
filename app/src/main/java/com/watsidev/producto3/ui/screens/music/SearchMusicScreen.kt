package com.watsidev.producto3.ui.screens.music

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.watsidev.producto3.R

@Composable
fun SearchMusicScreen(
    onClick: (Any) -> Unit
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
                    .fillMaxSize()
            ) {
                item {
                    SearchBarMusic(
                        value = "",
                        onValueChange = { },
                        username = "WatsiDev",
                    )
                }
            }
        }
    }
}