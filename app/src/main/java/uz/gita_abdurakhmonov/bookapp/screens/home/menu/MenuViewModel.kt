package uz.gita_abdurakhmonov.bookapp.screens.home.menu

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.syntax.simple.intent
import org.orbitmvi.orbit.syntax.simple.reduce
import org.orbitmvi.orbit.viewmodel.container
import uz.gita_abdurakhmonov.bookapp.data.BookData
import uz.gita_abdurakhmonov.bookapp.domain.Repository
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val direction: MenuContract.Direction,
    private val repository: Repository
) : MenuContract.ViewModel, ViewModel() {

    init {
        repository.getAll().onEach {
            val newBook = mutableListOf<BookData>()
            val oldBook = mutableListOf<BookData>()

            it.onSuccess { list ->
                list.forEachIndexed { index, bookData ->
                    if (index < 4) {
                        Log.d("AAA", "it: $bookData")
                        newBook.add(bookData)
                    } else {
                        oldBook.add(bookData)
                    }
                }
                intent { reduce { state.copy(newBooks = newBook,oldBooks = oldBook) } }
            }
        }.launchIn(viewModelScope)
    }

    override fun onEventDispatchers(intent: MenuContract.Intent) {
        when (intent) {
            is MenuContract.Intent.ClickItem -> {
                viewModelScope.launch {
                    direction.nextToDownload(intent.data)
                }
            }

            is MenuContract.Intent.ClickInfo -> {
                viewModelScope.launch {
                    direction.nextToInfo()
                }
            }

            is MenuContract.Intent.ClickNotification -> {
                viewModelScope.launch {

                }
            }
        }
    }

    override val container = container<MenuContract.UIState, MenuContract.SideEffect>(
        MenuContract.UIState(
            emptyList(),
            emptyList()
        )
    )
}