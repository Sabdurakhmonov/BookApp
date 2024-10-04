package uz.gita_abdurakhmonov.bookapp.screens.home.reader

import org.orbitmvi.orbit.ContainerHost

interface ReaderContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers()
    }

    data class UIState(val url:String)
    data class SideEffect(val message:String)

    interface Direction{
        suspend fun back()
    }
}