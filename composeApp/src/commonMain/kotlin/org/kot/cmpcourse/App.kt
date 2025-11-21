package org.kot.cmpcourse

import androidx.compose.runtime.Composable
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.kot.cmpcourse.multinavigation.MultiNavigationRootComponent
import org.kot.cmpcourse.multinavigation.MultiNavigationRootScreen
import org.kot.cmpcourse.navigation.RootComponent
import org.kot.cmpcourse.navigation.RootScreen
import org.kot.cmpcourse.scaffold.ScaffoldScreen

@Composable
@Preview
fun App(component: MultiNavigationRootComponent) {
    PostScreen()
}