package org.kot.cmpcourse

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.arkivanov.decompose.DefaultComponentContext
import com.arkivanov.essenty.lifecycle.LifecycleRegistry
import org.kot.cmpcourse.multinavigation.MultiNavigationRootComponent
import org.kot.cmpcourse.navigation.RootComponent

fun MainViewController() = ComposeUIViewController {
    val root = remember {
        MultiNavigationRootComponent(DefaultComponentContext(LifecycleRegistry()))
    }
    App(root)
}