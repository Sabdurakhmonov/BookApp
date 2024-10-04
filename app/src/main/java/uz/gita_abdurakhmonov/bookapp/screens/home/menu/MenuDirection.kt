package uz.gita_abdurakhmonov.bookapp.screens.home.menu

import uz.gita_abdurakhmonov.bookapp.data.BookData
import uz.gita_abdurakhmonov.bookapp.navigation.AppNavigator
import uz.gita_abdurakhmonov.bookapp.screens.home.download.DownloadScreen
import uz.gita_abdurakhmonov.bookapp.screens.home.info.InfoScreen
import javax.inject.Inject

class MenuDirection @Inject constructor(
    private val appNavigator: AppNavigator
):MenuContract.Direction {
    override suspend fun nextToInfo() {
        appNavigator.push(InfoScreen)
    }

    override suspend fun nextToDownload(data: BookData) {
        appNavigator.push(DownloadScreen(data))
    }
}