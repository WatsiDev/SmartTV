package com.watsidev.producto3.ui.screens.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun RecipeCard(
    recipe: Recipe,
    onToggleFavorite: (Recipe) -> Unit,
    onGoRecipe: (Int) -> Unit
) {
    Box(
        modifier = Modifier
            .clickable { onGoRecipe(recipe.id) }
            .width(220.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(Color.DarkGray)
            .focusable()
    ) {
        Column {
            Image(
                painter = painterResource(id = recipe.imageRes),
                contentDescription = recipe.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(140.dp)
                    .fillMaxWidth()
            )
            Text(
                text = recipe.name,
                color = Color.White,
                fontSize = 16.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(8.dp)
            )
        }

        // Ícono de favorito en la esquina superior derecha
        IconButton(
            onClick = {
                onToggleFavorite(recipe)
            },
            modifier = Modifier
                .clickable{ onToggleFavorite(recipe) }
                .align(Alignment.TopEnd)
                .padding(6.dp)
        ) {
            Icon(
                imageVector = if (recipe.isFavorite) Icons.Filled.Favorite else Icons.Outlined.Favorite,
                tint = if (recipe.isFavorite) Color.Red else Color.White,
                contentDescription = "Favorito"
            )
        }
    }
}
