package com.watsidev.producto3.ui.screens.recipe

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun RecipeDetailScreen(
    idRecipe: Int,
    onClick: (Any) -> Unit,
    showVideo: (Int) -> Unit
) {
    val recipe = remember(idRecipe) {
        recipesList.firstOrNull { it.id == idRecipe }
    }

    if (recipe == null) {
        Text(
            text = "Receta no encontrada",
            color = Color.White,
            modifier = Modifier.padding(32.dp)
        )
        return
    }

    Row(modifier = Modifier.fillMaxSize()) {
        SideNavigationBar(onClick = { onClick(it) })

        Column(
            modifier = Modifier
                .weight(1f)
                .verticalScroll(rememberScrollState())
                .padding(32.dp)
        ) {
            Text(
                text = recipe.name,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(24.dp))

                Column {
                    Image(
                        painter = painterResource(id = recipe.imageRes),
                        contentDescription = "Imagen de la receta",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(220.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Button(
                        onClick = { showVideo(recipe.videoRes) },
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .clickable{ showVideo(recipe.videoRes) }
                    ) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Ver video de preparación")
                    }
                }


            Spacer(modifier = Modifier.height(32.dp))

            Text("Ingredientes", fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            Spacer(modifier = Modifier.height(12.dp))
            recipe.ingredients.forEach {
                Text("• $it", fontSize = 18.sp, color = Color.LightGray)
            }

            Spacer(modifier = Modifier.height(32.dp))

            Text("Preparación", fontSize = 24.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
            Spacer(modifier = Modifier.height(12.dp))
            Text(recipe.preparation, fontSize = 18.sp, color = Color.LightGray, lineHeight = 28.sp)
        }
    }
}
