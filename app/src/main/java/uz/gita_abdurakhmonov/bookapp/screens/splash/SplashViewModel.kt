package uz.gita_abdurakhmonov.bookapp.screens.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val direction: SplashContract.Direction
) :SplashContract.ViewModel,ViewModel(){
    init {
        viewModelScope.launch{
            delay(1500)
            direction.nextToScreen()
        }
    }
    override val container = container<SplashContract.UIState, SplashContract.SideEffect>(SplashContract.UIState())
}