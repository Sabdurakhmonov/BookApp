package uz.gita_abdurakhmonov.bookapp.screens.home.menu

import org.orbitmvi.orbit.ContainerHost
import uz.gita_abdurakhmonov.bookapp.data.BookData

interface MenuContract {
    interface ViewModel : ContainerHost<UIState, SideEffect> {
        fun onEventDispatchers(intent: Intent)
    }

    data class UIState(
        val newBooks: List<BookData>,
        val oldBooks: List<BookData>
    )

    data class SideEffect(val message: String)

    sealed interface Intent {
        data class ClickItem(val data: BookData) : Intent
        data object ClickInfo : Intent
        data object ClickNotification : Intent
    }

    interface Direction {
        suspend fun nextToInfo()
        suspend fun nextToDownload(data: BookData)
    }
}