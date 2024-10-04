package uz.gita_abdurakhmonov.bookapp.screens.home.menu

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.AsyncImagePainter.State.Empty.painter
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.valentinilk.shimmer.shimmer
import org.intellij.lang.annotations.JdkConstants.VerticalScrollBarPolicy
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita_abdurakhmonov.bookapp.R
import uz.gita_abdurakhmonov.bookapp.data.BookData
import uz.gita_abdurakhmonov.bookapp.ui.theme.Cl
import uz.gita_abdurakhmonov.bookapp.ui.theme.component.AppInputTextComponent
import uz.gita_abdurakhmonov.bookapp.ui.theme.component.ItemDownload
import uz.gita_abdurakhmonov.bookapp.ui.theme.component.ItemGrid
import uz.gita_abdurakhmonov.bookapp.ui.theme.component.ItemTab
import uz.gita_abdurakhmonov.bookapp.ui.theme.component.ShimmerUi
import uz.gita_abdurakhmonov.bookapp.ui.theme.display
import uz.gita_abdurakhmonov.bookapp.ui.theme.inter
import androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope as LazyGridItemSpanScope

object MenuScreen : Screen {
    private fun readResolve(): Any = MenuScreen

    @Composable
    override fun Content() {
        val viewModel: MenuContract.ViewModel = getViewModel<MenuViewModel>()
        val uiState = viewModel.collectAsState()
        MenuScreenContent(uiState, viewModel::onEventDispatchers)
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun MenuScreenContent(
    uiState: State<MenuContract.UIState>,
    intent: (MenuContract.Intent) -> Unit
) {
    val inputValue = remember {
        mutableStateOf("")
    }
    val newBook = remember { mutableStateOf(uiState.value.newBooks) }
    val oldBook = remember { mutableStateOf(uiState.value.oldBooks) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Cl.bg)
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .height(56.dp)
                .background(color = Cl.bg)
                .padding(horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier.weight(1f),
                text = "My Books",
                fontSize = 20.sp,
                fontFamily = display(),
                fontWeight = FontWeight.Medium,
                color = Cl.textColor
            )
            Icon(
                modifier = Modifier.clickable(
                    interactionSource = MutableInteractionSource(),
                    indication = null
                ) { intent.invoke(MenuContract.Intent.ClickInfo)},
                painter = painterResource(id = R.drawable.ic_info),
                contentDescription = "",
                tint = Cl.textSearch
            )
        }

        if (newBook.value.isNotEmpty()) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxSize()
            ) {
                item(span = { GridItemSpan(2) }) {
                    Column(modifier = Modifier.padding(top = 8.dp)) {
                        AppInputTextComponent(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(horizontal = 8.dp),
                            hint = "search",
                            text = inputValue.value,
                            minHeight = 48.dp,
                            onValueChange = { inputValue.value = it },
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_search),
                                    contentDescription = "",
                                    modifier = Modifier.padding(end = 6.dp)
                                )
                            }
                        )
                    }
                }
                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "Continue Reading",
                        fontFamily = display(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 8.dp)
                            .padding(vertical = 12.dp)
                    )
                }
                item(span = { GridItemSpan(2) }) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    ) {
                        items(newBook.value.size) {
                            ItemDownload(newBook.value[it], listener = { data ->
                                intent.invoke(MenuContract.Intent.ClickItem(data))
                            })
                        }
                    }
                }

                item(span = { GridItemSpan(2) }) {
                    Text(
                        text = "Best Sellers",
                        fontFamily = display(),
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(horizontal = 16.dp)
                            .padding(vertical = 12.dp)
                    )
                }

                items(oldBook.value.size) {
                    ItemGrid(oldBook.value[it], listener = { data ->
                        intent.invoke(MenuContract.Intent.ClickItem(data))
                    })
                }

            }

        } else {
            ShimmerUi()
            newBook.value = uiState.value.newBooks
            oldBook.value = uiState.value.oldBooks
        }
    }
    val systemUiController = rememberSystemUiController()
    systemUiController.setStatusBarColor(Cl.bg, darkIcons = true)
}