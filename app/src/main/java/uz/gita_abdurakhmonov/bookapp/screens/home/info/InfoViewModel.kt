package uz.gita_abdurakhmonov.bookapp.screens.home.info

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject
@HiltViewModel
class InfoViewModel @Inject constructor(
    private val direction: InfoContract.Direction
):InfoContract.ViewModel,ViewModel() {
    override fun onEventDispatchers() {
        viewModelScope.launch {
            direction.back()
        }
    }

    override val container = container<InfoContract.UIState, InfoContract.SideEffect>(InfoContract.UIState())
}