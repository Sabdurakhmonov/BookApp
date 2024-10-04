package uz.gita_abdurakhmonov.bookapp.screens.home.info

import org.orbitmvi.orbit.ContainerHost

interface InfoContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{
        fun onEventDispatchers()
    }

    class UIState

    data class SideEffect(val message:String)

    sealed interface Intent{
        data object ClickBack:Intent
    }
    interface Direction{
        suspend fun back()
    }
}