package com.lakshit.malla.leomath


import android.app.Activity
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavHostController
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(MainActivity : Activity,navController: NavHostController){

    LaunchedEffect(key1 = MainActivity){
     delay(2000)
        navController.navigate("login")

    }
}

@Composable
fun Splash(windowSizeClass: WindowSizeClass,navController: NavHostController,MainActivity : Activity){
    SplashScreen(MainActivity = MainActivity, navController = navController)
    when (windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
            SplashImage(image = R.drawable.splash_port)
        }

        WindowWidthSizeClass.Expanded -> {
            SplashImage(image = R.drawable.splash_land)
        }

        WindowWidthSizeClass.Medium -> {
            SplashImage(image = R.drawable.splash_land)
        }
    }
}

@Composable
fun SplashImage(@DrawableRes image : Int){
    Column(modifier = Modifier.fillMaxSize()) {
        Image(painter = painterResource(id = image), contentDescription = "", contentScale = ContentScale.Crop)
    }
}