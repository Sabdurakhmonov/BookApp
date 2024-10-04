package uz.gita_abdurakhmonov.bookapp.screens.home.download

import org.orbitmvi.orbit.ContainerHost
import uz.gita_abdurakhmonov.bookapp.data.BookData

interface DownloadContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers(intent: Intent)
        fun checkData(data: BookData)
    }
    interface Direction{
        suspend fun back()
        suspend fun nextToReader(url:String)
    }
    data class UIState(
        val data:BookData?,
        val btnText:String = "Download",
        val loading:Boolean = false
    )
    data class SideEffect(val message:String)
    sealed interface Intent{
        data object ClickBack:Intent
        data class ClickDownload(val data:BookData) :Intent
        data object ClickNext:Intent
    }
}