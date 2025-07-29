package com.watsidev.producto3.ui.screens.music

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Logout
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.Share
import androidx.compose.material.icons.outlined.WorkspacePremium
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.watsidev.producto3.R

@Composable
fun MyAccountScreen(
    onClick: (Any) -> Unit
){
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
                    AccountInfo(modifier = Modifier.fillMaxSize())
                }
            }
        }
    }
}



@Composable
fun AccountInfo(
    modifier : Modifier = Modifier
){
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Image(
            painterResource(R.drawable.background),
            contentDescription = "Image Profile",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .shadow(8.dp, RoundedCornerShape(100))
                .size(125.dp)
                .aspectRatio(1f)
        )
        Text(
            "WatsiDev",
            fontSize = 25.sp,
            color = Color.White
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                "Share",
                fontWeight = FontWeight.Medium,
                fontSize = 18.sp,
                color = Color.White
            )
            Icon(
                Icons.Outlined.Share,
                contentDescription = null,
                tint = Color.White
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.CenterHorizontally),
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .shadow(8.dp)
                .background(Color.Black)
        ) {
            Text(
                "GET PREMIUM!",
                fontWeight = FontWeight.Bold,
                fontSize = 18.sp,
                color = Color(0xFFFFD700),
                modifier = Modifier
                    .padding(vertical = 8.dp)
            )
            Icon(
                Icons.Outlined.WorkspacePremium,
                contentDescription = null,
                tint = Color(0xFFFFD700)
            )
        }
        Spacer(Modifier.weight(1f))
        Row (
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End
        ){
            Icon(
                Icons.AutoMirrored.Outlined.Logout,
                contentDescription = null,
                tint = Color.White,
                modifier = Modifier
                    .padding(end = 16.dp, bottom = 32.dp)
            )
        }
    }
}
