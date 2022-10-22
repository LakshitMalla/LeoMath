package com.lakshit.malla.leomath

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
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@Composable
fun LoginImage(@DrawableRes image: Int) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "",
        contentScale = ContentScale.Crop
    )
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
                .padding(50.dp, 25.dp, 50.dp, 25.dp)
                .height(65.dp)
        )

    }


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
                .padding(50.dp, 25.dp, 50.dp, 25.dp)
                .height(65.dp)
        )

    }


}

@Composable
fun LoginButton(){
    ElevatedButton(onClick = { /*TODO*/ }, modifier = Modifier
        .padding(50.dp,25.dp,50.dp,25.dp)
        .clip(RoundedCornerShape(10.dp))
        .width(90.dp)) {
        Row( horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
            Text(text = "Login")
        }
    }
}

