package com.watsidev.producto3.ui.screens.menu

import com.watsidev.producto3.R
import com.watsidev.producto3.ui.navigation.Music
import com.watsidev.producto3.ui.navigation.Recipe
import com.watsidev.producto3.ui.navigation.Video

data class App(
    val id: Int,
    val imageRes: Int,
    val route: Any
)

val appsList = listOf(
    App(
        id = 1,
        imageRes = R.drawable.music,
        route = Music
    ),
    App(
        id = 2,
        imageRes = R.drawable.youtube,
        route = Video
    ),
    App(
        id = 3,
        R.drawable.chef,
        route = Recipe
    )
)