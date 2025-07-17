package com.watsidev.producto3

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Surface
import com.watsidev.producto3.ui.navigation.Cover
import com.watsidev.producto3.ui.navigation.Home
import com.watsidev.producto3.ui.navigation.Menu
import com.watsidev.producto3.ui.screens.CoverScreen
import com.watsidev.producto3.ui.screens.HomeScreen
import com.watsidev.producto3.ui.screens.MenuAppScreen
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
    Surface(
        modifier = Modifier
            .fillMaxSize()
    ) {
        NavHost(
            navController = navController,
            startDestination = Cover
        ) {
            composable<Cover> {
                CoverScreen(
                    onClick = {navController.navigate(Menu) }
                )
            }
            composable<Home> {
                HomeScreen()
            }
            composable<Menu> {
                MenuAppScreen()
            }
        }
    }
}