package uz.gita_abdurakhmonov.bookapp.screens.home.download

import uz.gita_abdurakhmonov.bookapp.navigation.AppNavigator
import uz.gita_abdurakhmonov.bookapp.screens.home.reader.ReaderScreen
import javax.inject.Inject

class DownloadDirection @Inject constructor(
    private val appNavigator: AppNavigator
):DownloadContract.Direction {
    override suspend fun back() {
        appNavigator.back()
    }

    override suspend fun nextToReader(url: String) {
        appNavigator.replace(ReaderScreen(url))
    }
}