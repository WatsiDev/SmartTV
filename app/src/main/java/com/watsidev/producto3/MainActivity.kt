package com.watsidev.producto3

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.watsidev.producto3.ui.navigation.Cover
import com.watsidev.producto3.ui.navigation.Menu
import com.watsidev.producto3.ui.navigation.Music
import com.watsidev.producto3.ui.navigation.Recipe
import com.watsidev.producto3.ui.navigation.Video
import com.watsidev.producto3.ui.screens.CoverScreen
import com.watsidev.producto3.ui.screens.home.VideoScreen
import com.watsidev.producto3.ui.screens.menu.MenuAppScreen
import com.watsidev.producto3.ui.screens.music.MusicScreen
import com.watsidev.producto3.ui.screens.recipe.Detail
import com.watsidev.producto3.ui.screens.recipe.Favorites
import com.watsidev.producto3.ui.screens.recipe.FavoritesScreen
import com.watsidev.producto3.ui.screens.recipe.FullScreenVideoPlayer
import com.watsidev.producto3.ui.screens.recipe.RecipeDetailScreen
import com.watsidev.producto3.ui.screens.recipe.RecipeHomeScreen
import com.watsidev.producto3.ui.screens.recipe.RecipeViewModel
import com.watsidev.producto3.ui.screens.recipe.VideoRecipe
import com.watsidev.producto3.ui.theme.Producto3Theme

class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Producto3Theme {
                App()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun App() {
    val navController = rememberNavController()
    val recipeViewModel: RecipeViewModel = viewModel()

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Cover
        ) {
            composable<Cover> {
                CoverScreen(
                    onClick = { navController.navigate(Menu) }
                )
            }
            composable<Menu> {
                MenuAppScreen(
                    onAppClick = { app -> navController.navigate(app) }
                )
            }
            composable<Music> {
                MusicScreen()
            }
            composable<Video> {
                VideoScreen()
            }
            composable<Recipe> {
                RecipeHomeScreen(
                    viewModel = recipeViewModel,
                    onClick = { route -> navController.navigate(route) },
                    onGoRecipe = { id -> navController.navigate(Detail(id)) }
                )
            }
                composable<Favorites> {
                    FavoritesScreen(
                        viewModel = recipeViewModel,
                        onClick = { route -> navController.navigate(route) },
                        onGoRecipe = { idRecipe -> navController.navigate(Detail(idRecipe)) }
                    )
                }
                composable<Detail> {
                    val detail: Detail = it.toRoute()
                    RecipeDetailScreen(
                        idRecipe = detail.id,
                        onClick = { route -> navController.navigate(route) },
                        showVideo = { idRes -> navController.navigate(VideoRecipe(idRes)) }
                    )
                }
                composable<VideoRecipe> {
                    val videoRecipe: VideoRecipe = it.toRoute()
                    FullScreenVideoPlayer(
                        resId = videoRecipe.id,
                        onClose = { navController.popBackStack() }
                    )
                }
        }
    }
}
