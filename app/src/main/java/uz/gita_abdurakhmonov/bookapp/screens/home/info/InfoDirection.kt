package uz.gita_abdurakhmonov.bookapp.screens.home.info

import uz.gita_abdurakhmonov.bookapp.navigation.AppNavigator
import javax.inject.Inject

class InfoDirection @Inject constructor(
    private val appNavigator: AppNavigator
):InfoContract.Direction {
    override suspend fun back() {
        appNavigator.back()
    }
}