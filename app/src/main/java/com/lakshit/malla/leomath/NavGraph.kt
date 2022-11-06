package com.lakshit.malla.leomath

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavGraph(
    MainActivity: Activity,
    navController: NavHostController,
    windowSizeClass: WindowSizeClass,
    mAuth: FirebaseAuth,
    signIn: () -> Unit
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
            Splash(windowSizeClass = windowSizeClass, navController = navController, MainActivity = MainActivity)

        }
        composable("login") {
            Login(windowSizeClass = windowSizeClass, mAuth = mAuth, signIn = { signIn }, navController = navController)

        }
        composable("onBoarding") {
            OnBoarding()
        }
    }
}