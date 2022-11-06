package com.lakshit.malla.leomath

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lakshit.malla.leomath.ui.theme.Shape

@ExperimentalMaterial3Api
@Composable
fun GoogleButton(
    text: String = "Sign Up With Google",
    loadingText: String = "Creating Account.....",
    signInClicked: ()-> Unit


) {

var clickable by remember {
    mutableStateOf(false)
}

    Surface(
        onClick = {  signInClicked()
                  clickable = !clickable},
        shape = Shape.large,
        border = BorderStroke(width = 1.dp, color = Color.White
        ),
        shadowElevation = 10.dp,
        color = MaterialTheme.colorScheme.surface,


    ) {

        Row(
            modifier = Modifier
                .padding(
                    start = 15.dp,
                    end = 19.dp,
                    top = 15.dp,
                    bottom = 15.dp
                )
                .animateContentSize(
                    animationSpec = tween(
                        durationMillis = 300,
                        easing = LinearOutSlowInEasing
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "Google Button",
                tint = Color.Unspecified
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if(clickable)loadingText else text)
            if (clickable) {
                Spacer(modifier = Modifier.width(16.dp))
                CircularProgressIndicator(
                    modifier = Modifier
                        .width(12.dp)
                        .height(12.dp),
                    strokeWidth = 2.dp,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

    }
}



