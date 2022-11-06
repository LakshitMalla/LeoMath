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
import com.lakshit.malla.leomath.ui.theme.Green300
import com.lakshit.malla.leomath.ui.theme.Green500
import com.lakshit.malla.leomath.ui.theme.YellowGreen300
import kotlin.math.log

@Composable
fun LoginImage(@DrawableRes image: Int) {
    Column(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "",
            contentScale = ContentScale.Crop, modifier = Modifier.fillMaxSize()

        )
    }
}

@Composable
fun Username() {


    var username by remember { mutableStateOf(TextFieldValue("")) }


    Row(horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value = username,
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
fun UsernameTFPreview() {
    Username()
}

@Composable
fun Password() {


    var password by remember { mutableStateOf(TextFieldValue("")) }

    Row(horizontalArrangement = Arrangement.Center) {
        OutlinedTextField(
            value = password,
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
fun LoginButton(navHostController: NavHostController) {


    var isPress = false


    val button_colour = ButtonDefaults.buttonColors(
        containerColor = Color(0xFFfedd56),
        contentColor = Color(0xFFce681d)
    )
    ElevatedButton(
        onClick = {

            navHostController.navigate("onBoarding")

            isPress = !isPress


        }, modifier = Modifier

            .clip(RoundedCornerShape(10.dp))
            .width(90.dp), colors = button_colour,

    ) {
        Text(text = "Login")





    }
    if (isPress == true){
        navHostController.navigate("onBoarding")
    }else{
        Log.d("Log","Is working")
    }
}






