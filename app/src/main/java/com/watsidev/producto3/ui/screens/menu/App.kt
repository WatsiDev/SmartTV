package com.watsidev.producto3.ui.screens.menu

import android.content.Context
import android.widget.Toast
import com.watsidev.producto3.R
import com.watsidev.producto3.ui.navigation.Clima
import com.watsidev.producto3.ui.navigation.Game
import com.watsidev.producto3.ui.navigation.Music
import com.watsidev.producto3.ui.navigation.Recipe
import com.watsidev.producto3.ui.navigation.Video

data class App(
    val id: Int,
    val imageRes: Int,
    val route: Any?
)

val appsList = listOf(
    App(
        id = 1,
        imageRes = R.drawable.music_banner,
        route = Music
    ),
    App(
        id = 2,
        imageRes = R.drawable.video_banner,
        route = Video
    ),
    App(
        id = 3,
        R.drawable.chef_banner,
        route = Recipe
    ),
    App(
        id = 4,
        R.drawable.weather_banner,
        route = Clima
    ),
    App(
        id = 5,
        R.drawable.game_banner,
        route = Game
    ),
)

val intentAppList = listOf(
    App(
        id = 1,
        imageRes = R.drawable.youtube_banner,
        route = "com.google.android.youtube.tv"
    ),
    App(
        id = 2,
        imageRes = R.drawable.netflix_banner,
        route = "com.netflix.netflix.tv"
    ),
    App(
        id = 3,
        imageRes = R.drawable.prime_video_banner,
        route = "com.amazon.amazonvideo.livingroom"
    ),
)

val settingsIcons = listOf(
    App(
        id = 1,
        imageRes = R.drawable.play_tv_banner,
        route = null
    ),
    App(
        id = 2,
        imageRes = R.drawable.usb_banner,
        route = null
    ),
    App(
        id = 3,
        imageRes = R.drawable.hdmi_banner,
        route = null
    ),
    App(
        id = 4,
        imageRes = R.drawable.record_banner,
        route = null
    ),
)

fun openExternalApp(context: Context, packageName: String) {
    val launchIntent = context.packageManager.getLaunchIntentForPackage(packageName)
    if (launchIntent != null) {
        context.startActivity(launchIntent)
    } else {
        Toast.makeText(context, "La app no está instalada", Toast.LENGTH_SHORT).show()
    }
}

