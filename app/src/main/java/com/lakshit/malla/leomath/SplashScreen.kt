package com.lakshit.malla.leomath


import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(MainActivity : Activity,navController: NavHostController){

    LaunchedEffect(key1 = MainActivity){
     delay(5000)
        navController.navigate("login")

    }
}

@Composable
fun SplashImage(@DrawableRes image : Int){
    Image(painter = painterResource(id = image), contentDescription = "", contentScale = ContentScale.Crop)
}