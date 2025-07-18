package com.watsidev.producto3.ui.screens.recipe

import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.annotation.RawRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.RawResourceDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.ui.PlayerView
import androidx.core.net.toUri
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton

@kotlin.OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun FullScreenVideoPlayer(
    @RawRes resId: Int,
    onClose: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        VideoPlayer(resId)

        IconButton(
            onClick = onClose,
            modifier = Modifier
                .clickable{ onClose() }
                .align(Alignment.TopEnd)
                .padding(16.dp)
                .background(Color(0x66000000), shape = CircleShape)
        ) {
            Icon(
                imageVector = Icons.Default.Close,
                contentDescription = "Cerrar video",
                tint = Color.White,
                modifier = Modifier.size(32.dp)
            )
        }
    }
}

@OptIn(UnstableApi::class)
@Composable
fun VideoPlayer(resId: Int, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            val mediaItem = MediaItem.fromUri(
                "android.resource://${context.packageName}/$resId".toUri()
            )
            setMediaItem(mediaItem)
            prepare()
            volume = 1f
            playWhenReady = true
        }
    }

    AndroidView(
        factory = {
            PlayerView(it).apply {
                player = exoPlayer
                useController = true
                layoutParams = FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT
                )
            }
        },
        modifier = modifier.fillMaxSize()
    )

    DisposableEffect(Unit) {
        onDispose {
            exoPlayer.release()
        }
    }
}
