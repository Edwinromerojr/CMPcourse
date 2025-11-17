package org.kot.cmpcourse

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.arkivanov.decompose.retainedComponent
import org.kot.cmpcourse.multinavigation.MultiNavigationRootComponent
import org.kot.cmpcourse.navigation.RootComponent

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        val root = retainedComponent { context ->
            MultiNavigationRootComponent(context)
        }

        setContent {
            App(root)
        }
    }
}