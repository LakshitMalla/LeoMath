package com.lakshit.malla.leomath

import android.app.Activity
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.ExperimentalMaterial3WindowSizeClassApi
import androidx.compose.material3.windowsizeclass.WindowSizeClass
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.material3.windowsizeclass.calculateWindowSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.google.firebase.auth.FirebaseAuth
import kotlin.math.log

@Composable
fun LoginImage(@DrawableRes image: Int) {
   Column(modifier = Modifier.fillMaxSize()) {
       Image(
           painter = painterResource(id = image),
           contentDescription = "",
           contentScale = ContentScale.Crop
       )
   }
}

@Composable
fun Username() {


    var username by remember { mutableStateOf(TextFieldValue("")) }


    Row(horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(value = username,
            onValueChange = { newText ->
                username = newText
            },
            placeholder = { Text(text = "Username", fontWeight = FontWeight.Bold) },
            label = { Text(text = "Username", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth()
        )

    }


}

@Preview
@Composable
fun UsernameTFPreview(){
    Username()
}

@Composable
fun Password() {


    var password by remember { mutableStateOf(TextFieldValue("")) }

    Row(horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(value = password,
            onValueChange = { newText ->
                password = newText
            },
            placeholder = { Text(text = "Password", fontWeight = FontWeight.Bold) },
            label = { Text(text = "Password", fontWeight = FontWeight.Bold) },
            modifier = Modifier
                .fillMaxWidth()
        )

    }


}

@Composable
fun LoginButton(navHostController: NavHostController){
    var isClicked = false
    val button_colour = ButtonDefaults.buttonColors(
        containerColor = Color("#d5e6ff".toColorInt()),
        contentColor = Color("#488af4".toColorInt())
    )
    ElevatedButton(onClick = {

            navHostController.navigate("onBoarding")

    }, modifier = Modifier

        .clip(RoundedCornerShape(10.dp))
        .width(90.dp), colors = button_colour) {
        Text(text = "Login")

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(windowSizeClass: WindowSizeClass,mAuth : FirebaseAuth, signIn: () -> Unit,navController: NavHostController){
    when(windowSizeClass.widthSizeClass) {
        WindowWidthSizeClass.Compact -> {
        LoginImage(image = R.drawable.login_port)
    }

        WindowWidthSizeClass.Expanded -> {
        LoginImage(image = R.drawable.login_land)
    }

        WindowWidthSizeClass.Medium -> {
        LoginImage(image = R.drawable.login_port)
    }
    }
    Row(modifier = Modifier.fillMaxSize(), verticalAlignment = Alignment.CenterVertically) {


        Column(
            verticalArrangement = Arrangement.SpaceEvenly, modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.55f)
                .padding(22.dp, 7.dp)
        ) {

            Username()
            Password()



            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center
            ) {

                if (mAuth.currentUser == null) {
                    GoogleButton {
                        Log.d("", "SignIn")
                        signIn()

                    }
                } else {
                    /*GoogleButton {
                        Log.d("","SignIn")
                        signIn()

                    }*/
                    navController.navigate("onBoarding")
                }


            }

            Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                LoginButton(navController)
            }

        }
    }
        }




