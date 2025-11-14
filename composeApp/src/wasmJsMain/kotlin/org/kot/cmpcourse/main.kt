package org.kot.cmpcourse

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.browser.document
import org.kot.cmpcourse.navigation.RootComponent

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    val lifecycle = LifecycleRegistry()
    val rootComponent = RootComponent (
        DefaultComponentContext(lifecycle)
    )

    ComposeViewport(document.body!!) {
        App(rootComponent)
    }
}