package org.kot.cmpcourse

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cmpcourse.composeapp.generated.resources.Res
import cmpcourse.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
//    Box(modifier = Modifier.fillMaxSize().background(Color.LightGray)) {
//        Text(
//            text = "Hello World 1",
//            modifier = Modifier.align(Alignment.Center)
//            )
//    }
//    Column(
//        modifier = Modifier.fillMaxSize().background(Color.White),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ){
//        Text(
//            text = "My Name is json"
//        )
//        Text(
//            text = "My age is 30"
//        )
//    }
    Row(
        modifier = Modifier.fillMaxSize().background(Color.White),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment =  Alignment.CenterVertically
    ) {
        Text(
            text = "My Name is json"
        )
        Text(
            text = "Text"
        )
        Text(
            text = "My age is 30",
            modifier = Modifier.clickable{
                println("Text Clicked!!")
            }
        )

        Button(onClick = {
            println("Button Clicked!!")
        }){
            Text("Click")
        }
    }
}