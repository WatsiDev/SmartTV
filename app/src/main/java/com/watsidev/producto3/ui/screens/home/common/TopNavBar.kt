package com.watsidev.producto3.ui.screens.home.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Icon
import androidx.tv.material3.IconButton
import androidx.tv.material3.Text
import com.watsidev.producto3.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TopNavBar(
    onHomeClick: () -> Unit,
    value: String,
    onValueChange: (String) -> Unit,
    onSearchMovie: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "slothui",
                color = Color.White,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(24.dp))
            Text("Home", Modifier
                .padding(end = 16.dp)
                .clickable { onHomeClick() }, color = Color.White)
            Text("Browse", Modifier.padding(end = 16.dp), color = Color.White)
            Text("Kids", Modifier.padding(end = 16.dp), color = Color.White)
            Text("Support", Modifier.padding(end = 16.dp), color = Color.White)
            Text("FAQ", color = Color.White)
        }

        Row(modifier = Modifier.padding(bottom = 10.dp), verticalAlignment =  Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(
                value = value,
                onValueChange = { onValueChange(it) },
                placeholder = { Text("Search") },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    unfocusedBorderColor = Color.DarkGray,
                    focusedBorderColor = Color.DarkGray,
                    focusedTextColor = Color.White,
                    unfocusedTextColor = Color.White
                ),
                shape = RoundedCornerShape(100),
                modifier = Modifier
                    .width(180.dp)
            )
            IconButton(onClick = {}, modifier = Modifier.clickable{ onSearchMovie(value) }) {
                Icon(Icons.Default.Search, contentDescription = "Search", tint = Color.White)
            }
            Spacer(modifier = Modifier.width(8.dp))
            Image(
                painter = painterResource(id = R.drawable.avatar), // imagen del usuario
                contentDescription = "User avatar",
                modifier = Modifier
                    .size(36.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text("watsibienwatsimal", color = Color.White)
        }
    }
}