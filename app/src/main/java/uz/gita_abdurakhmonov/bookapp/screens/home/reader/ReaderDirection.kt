package uz.gita_abdurakhmonov.bookapp.screens.home.reader

import uz.gita_abdurakhmonov.bookapp.navigation.AppNavigator
import javax.inject.Inject

class ReaderDirection @Inject constructor(
    private val appNavigator: AppNavigator
):ReaderContract.Direction {
    override suspend fun back() {
        appNavigator.back()
    }
}