package uz.gita_abdurakhmonov.bookapp.screens.home.reader

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
class ReaderViewModel @Inject constructor(
    private val direction: ReaderContract.Direction
):ViewModel(),ReaderContract.ViewModel {
    override fun onEventDispatchers() {
        viewModelScope.launch {
            direction.back()
        }
    }

    override val container = container<ReaderContract.UIState, ReaderContract.SideEffect>(ReaderContract.UIState(""))
}