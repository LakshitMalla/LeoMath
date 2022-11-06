package com.lakshit.malla.leomath

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun OnBoarding() {

    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.surface) {
        Row(modifier = Modifier.fillMaxSize(), ) {
            Column(modifier = Modifier.fillMaxSize(), verticalArrangement = Arrangement.SpaceEvenly) {

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .height(85.dp)
                    .padding(0.dp, 15.dp, 0.dp, 15.dp)) {
                    Image(
                        painter = painterResource(id = R.drawable.boy_button),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(0.5f)
                            .fillMaxHeight()
                            .padding(0.dp)
                    )
                    Image(
                        painter = painterResource(id = R.drawable.girl_button),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .fillMaxHeight()
                            .padding(0.dp)
                    )

                }
                Column(modifier = Modifier.padding(35.dp,0.dp,0.dp,0.dp)) {
                    Text(
                        text = "Hello , ",
                        fontSize = 35.sp,
                        fontWeight = FontWeight.ExtraBold,
                        modifier = Modifier.padding(0.dp,0.dp,12.dp,0.dp)

                    )
                    Text(
                        text = "Welcome to Leo Math ",
                        fontSize = 27.sp,
                        fontWeight = FontWeight.Normal,
                        modifier = Modifier.padding(0.dp,0.dp,12.dp,0.dp)

                    )

                }

                Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Bottom) {

                    Image(
                        painter = painterResource(id = R.drawable.leo_cycling),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    )



                }

                var rememberValue by remember {
                    mutableStateOf(5)
                }
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    ListItemPicker(
                        value = rememberValue,
                        onValueChange = { rememberValue = it },
                        list = (3..8).toList(), textStyle = TextStyle(color = Color.Black.copy(alpha = 0.7f))
                    )
                }

            }









        }
        }



}

@Composable
fun Buttons(){
Button(onClick = { /*TODO*/ }, modifier = Modifier.fillMaxWidth().padding(7.dp)) {
    
}
}

@Preview
@Composable
fun PreviewOnBoarding() {
    OnBoarding()
}