package org.kot.cmpcourse

import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.window.ComposeViewport
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import kotlinx.browser.document
import org.koin.core.context.startKoin
import org.kot.cmpcourse.di.appModule
import org.kot.cmpcourse.multinavigation.MultiNavigationRootComponent
import org.kot.cmpcourse.navigation.RootComponent

@OptIn(ExperimentalComposeUiApi::class)
fun main() {
    startKoin {
        modules(appModule)
    }
    val lifecycle = LifecycleRegistry()
    val rootComponent = MultiNavigationRootComponent (
        DefaultComponentContext(lifecycle)
    )

    ComposeViewport(document.body!!) {
        App(rootComponent)
    }
}