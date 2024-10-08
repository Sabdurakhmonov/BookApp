package uz.gita_abdurakhmonov.bookapp.screens.splash

import uz.gita_abdurakhmonov.bookapp.navigation.AppNavigator
import uz.gita_abdurakhmonov.bookapp.screens.home.menu.MenuScreen
import javax.inject.Inject

class SplashDirection @Inject constructor(
    private val appNavigator: AppNavigator
) :SplashContract.Direction{
    override suspend fun nextToScreen() {
        appNavigator.replace(MenuScreen)
    }
}