package org.kot.cmpcourse.multinavigation

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.slot.SlotNavigation
import com.arkivanov.decompose.router.slot.childSlot
import com.arkivanov.decompose.router.slot.navigate
import com.arkivanov.decompose.router.stack.StackNavigation
import com.arkivanov.decompose.router.stack.childStack
import com.arkivanov.decompose.router.stack.pop
import com.arkivanov.decompose.router.stack.pushNew
import com.arkivanov.decompose.router.stack.replaceAll
import kotlinx.serialization.Serializable

class MultiNavigationRootComponent(context: ComponentContext) : ComponentContext by context {

    val navigation = StackNavigation<RootConfiguration>()

    val childStack = childStack(
        source = navigation,
        initialConfiguration = RootConfiguration.Auth,
        childFactory = ::createRootChild,
        serializer = RootConfiguration.serializer(),
        key = "MultiNavigation"
    )

    private fun createRootChild(configuration: RootConfiguration, context: ComponentContext): RootChild{
        return when (configuration) {
            is RootConfiguration.Auth -> {
                val authComponent = AuthComponent(
                    context = context,
                    onAuthSuccess = {
                        navigation.replaceAll(RootConfiguration.Main)
                    }
                )
                RootChild.Auth(authComponent)
            }
            is RootConfiguration.Main -> {
                val mainComponent = MainComponent(context)
                RootChild.Main(mainComponent)
            }
        }
    }

    @Serializable
    sealed class  RootConfiguration {
        @Serializable
        data object Auth : RootConfiguration()
        @Serializable
        data object Main : RootConfiguration()
    }

    sealed class RootChild {
        class Auth(val component: AuthComponent) : RootChild()
        class Main(val component: MainComponent) : RootChild()
    }
}

class AuthComponent(context: ComponentContext, private val onAuthSuccess: () -> Unit) : ComponentContext by context {

    val navigation = StackNavigation<AuthScreen>()

    val childStack = childStack(
        source = navigation,
        initialConfiguration = AuthScreen.Welcome,
        childFactory = ::createAuthChild,
        serializer = AuthScreen.serializer()
    )

    private fun createAuthChild(screen: AuthScreen, context: ComponentContext): AuthChild {
        return when (screen) {
            is AuthScreen.Welcome -> {
                val component = WelcomeComponent(
                    context = context,
                    navigateToRegister = {
                        navigation.pushNew(AuthScreen.Register)
                    },
                    navigateToLogin = {
                        navigation.pushNew(AuthScreen.Login)
                    }
                )
                AuthChild.Welcome(component)
            }

            is AuthScreen.Register -> {
                val component = RegisterComponent(
                    context = context,
                    onBack = {
                        navigation.pop()
                    },
                    navigateToMain = {
                        onAuthSuccess()
                    }
                )
                AuthChild.Register(component)
            }

            is AuthScreen.Login -> {
                val component = LoginComponent(
                    context = context,
                    onBack = {
                        navigation.pop()
                    },
                    navigateToMain = {
                        onAuthSuccess()
                    }
                )
                AuthChild.Login(component)
            }
        }
    }

    @Serializable
    sealed class AuthScreen {
        @Serializable
        data object Welcome : AuthScreen()
        @Serializable
        data object Register : AuthScreen()
        @Serializable
        data object Login : AuthScreen()
    }

    sealed class AuthChild{
        class Welcome(val component: WelcomeComponent) : AuthChild()
        class Register(val component: RegisterComponent) : AuthChild()
        class Login(val component: LoginComponent) : AuthChild()
    }
}

class WelcomeComponent(
    context: ComponentContext,
    private val navigateToRegister: () -> Unit,
    private val navigateToLogin: () -> Unit
) : ComponentContext by context {

    fun goToRegister(){
        navigateToRegister()
    }

    fun goToLogin(){
        navigateToLogin()
    }
}

class RegisterComponent(
    context: ComponentContext,
    private val onBack: () -> Unit,
    private val navigateToMain: () -> Unit
) : ComponentContext by context {

    fun goBack(){
        onBack()
    }

    fun register(){
        //register

        navigateToMain()
    }
}

class LoginComponent(
    context: ComponentContext,
    private val onBack: () -> Unit,
    private val navigateToMain: () -> Unit
) : ComponentContext by context {

    fun goBack(){
        onBack()
    }

    fun login(){
        //login

        navigateToMain()
    }
}

class MainComponent(context: ComponentContext) : ComponentContext by context {

    val navigation = StackNavigation<MainConfiguration>()

    val childStack = childStack(
        source = navigation,
        initialConfiguration = MainConfiguration.MainTabs,
        childFactory = ::createMainChild,
        serializer = MainConfiguration.serializer()
    )

    private fun createMainChild(configuration: MainConfiguration, context: ComponentContext): MainChild {
        return when (configuration) {
            is MainConfiguration.MainTabs -> {
                val component = MainTabsComponent(
                    context = context,
                    navigateToDetail = {
                        navigation.pushNew(MainConfiguration.Detail)
                    }
                )
                MainChild.MainTabs(component)
            }

            is MainConfiguration.Detail -> {
                val component = DetailComponent(
                    context = context,
                    onBack = {
                        navigation.pop()
                    }
                )
                MainChild.Detail(component)
            }
        }
    }

    @Serializable
    sealed class MainConfiguration {
        @Serializable
        data object MainTabs : MainConfiguration()
        @Serializable
        data object Detail : MainConfiguration()
    }

    sealed class MainChild {
        class MainTabs(val component: MainTabsComponent) : MainChild()
        class Detail(val component: DetailComponent) : MainChild()
    }
}

class MainTabsComponent(context: ComponentContext, private val navigateToDetail: () -> Unit) : ComponentContext by context {
    enum class Tab {
        HOME, PROFILE
    }

    private val selectedTab = SlotNavigation<Tab>()

    val child = childSlot(
        source = selectedTab,
        serializer = null,
        initialConfiguration = {
            Tab.HOME
        },
        childFactory = ::createMainTabsChild,
        key = "MainTabs"
    )

    fun selectedTab(tab: Tab){
        selectedTab.navigate { tab }
    }

    private fun createMainTabsChild(tab: Tab, context: ComponentContext): MainTabsChild {
        return when (tab) {
            Tab.HOME -> {
                val component = HomeComponent(
                    context = context,
                    navigateToDetail = {
                        navigateToDetail()
                    }
                )
                MainTabsChild.Home(
                    component
                )
            }

            Tab.PROFILE -> {
                val component = ProfileComponent(context)
                MainTabsChild.Profile(
                    component
                )
            }
        }
    }

    sealed class MainTabsChild {
        class Home(val component: HomeComponent) : MainTabsChild()
        class Profile(val component: ProfileComponent) : MainTabsChild()
    }
}

class HomeComponent(
    context: ComponentContext,
    private val navigateToDetail: () -> Unit,
) : ComponentContext by context {
    fun goToDetail(){
        navigateToDetail()
    }
}

class ProfileComponent(context: ComponentContext) : ComponentContext by context {

}

class DetailComponent(context: ComponentContext, private val onBack: () -> Unit) : ComponentContext by context {
    fun goBack() {
        onBack()
    }
}