package uz.gita_abdurakhmonov.bookapp.screens.splash

import org.orbitmvi.orbit.ContainerHost

interface SplashContract {
    interface ViewModel:ContainerHost<UIState,SideEffect>{

    }
    data class UIState(val id:String = "")
    data class SideEffect(val message:String)
    interface Direction{
         suspend fun nextToScreen()
    }
}