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
    mAuth : FirebaseAuth,
    signIn : ()-> Unit
) {
    NavHost(navController = navController, startDestination = "splash") {
        composable("splash") {
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
        composable("login") {
            when (windowSizeClass.widthSizeClass) {
                WindowWidthSizeClass.Compact -> {
                    LoginImage(image = R.drawable.login_land)
                }

                WindowWidthSizeClass.Expanded -> {
                    LoginImage(image = R.drawable.login_land)
                }

                WindowWidthSizeClass.Medium -> {
                    LoginImage(image = R.drawable.login_land)
                }
            }
            Column(verticalArrangement = Arrangement.Center, modifier = Modifier.fillMaxSize()) {
                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.7f), color = Color.Transparent
                ) {
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        Username()
                        Password()
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {

                            if(mAuth.currentUser == null){
                                GoogleButton {
                                    Log.d("","SignIn")
                                    signIn()

                                }
                            }else{
                                /*GoogleButton {
                                    Log.d("","SignIn")
                                    signIn()

                                }*/
                               navController.navigate("onBoarding")
                            }

                                LoginButton()

                        }

                    }

                }
            }
        }
        composable("onBoarding"){
            OnBoarding()
        }
    }
}