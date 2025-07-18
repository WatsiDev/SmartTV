package com.watsidev.producto3.ui.screens.recipe

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.Text
import com.watsidev.producto3.ui.navigation.Menu
import com.watsidev.producto3.ui.navigation.Recipe

data class routesRecipes(
    val id:  Int,
    val name: String,
    val icon: ImageVector? = null,
    val route: Any
)

val routesRecipesList = listOf(
    routesRecipes(
        1,
        "Home",
        Icons.Outlined.Home,
        Recipe
    ),
    routesRecipes(
        2,
        "Favorites",
        Icons.Outlined.Favorite,
        Favorites
    ),
    routesRecipes(
        3,
        name = "Salir",
        Icons.Outlined.Close,
        Menu
    )
)
@Composable
fun SideNavigationBar(onClick: (Any) -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .width(100.dp)
            .background(Color(0xFF121212)),
        verticalArrangement = Arrangement.spacedBy(32.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        routesRecipesList.forEach { route ->
            NavigationIcon(
                route = route,
                onClick = { onClick(it) }
            )
        }
    }
}

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun NavigationIcon(route: routesRecipes, onClick: (Any) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        route.icon?.let {
            Icon(
                imageVector = it,
                contentDescription = route.name,
                tint = Color.White,
                modifier = Modifier
                    .clickable{ onClick(route.route) }
                    .size(32.dp)
                    .focusable()
            )
        }
        Text(
            text = route.name,
            color = Color.White,
            fontSize = 12.sp
        )
    }
}
