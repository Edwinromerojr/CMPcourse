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
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview

import cmpcourse.composeapp.generated.resources.Res
import cmpcourse.composeapp.generated.resources.compose_multiplatform

@Composable
@Preview
fun App() {
    var isShown by remember {
        mutableStateOf(false)
    }

    Column(modifier = Modifier.fillMaxSize().background(Color.White)) {
        Button(onClick = {
            isShown = !isShown
            println("clicked")
        }){
            Text(text = if (isShown) "Hide Square" else "Show Square")
        }

        if (isShown) {
            Box(modifier = Modifier.size(100.dp).background(Color.Blue))
        }
    }
}