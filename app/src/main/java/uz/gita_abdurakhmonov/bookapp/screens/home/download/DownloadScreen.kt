package uz.gita_abdurakhmonov.bookapp.screens.home.download

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Card
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.Icon
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import coil.compose.rememberAsyncImagePainter
import org.orbitmvi.orbit.compose.collectAsState
import uz.gita_abdurakhmonov.bookapp.R
import uz.gita_abdurakhmonov.bookapp.data.BookData
import uz.gita_abdurakhmonov.bookapp.ui.theme.Cl
import uz.gita_abdurakhmonov.bookapp.ui.theme.display
import uz.gita_abdurakhmonov.bookapp.ui.theme.inter

data class DownloadScreen(private val data: BookData) : Screen {
    @Composable
    override fun Content() {
        val viewModel: DownloadViewModel = getViewModel<DownloadViewModel>()
        viewModel.checkData(data)
        val uiState = viewModel.collectAsState()
        DownloadScreenContent(data, uiState, viewModel::onEventDispatchers)
    }
}


@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun DownloadScreenContent(
    data: BookData,
    uiState: State<DownloadContract.UIState>,
    intent: (DownloadContract.Intent) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_1),
            contentDescription = "img",
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f), contentScale = ContentScale.Crop
        )
        Column(
            modifier = Modifier
                .fillMaxHeight(0.5f)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = data.bookTitle,
                fontSize = 26.sp,
                color = Cl.textColor,
                fontWeight = FontWeight.Bold,
                fontFamily = display(),
                modifier = Modifier.padding(top = 24.dp).padding(horizontal = 60.dp),
                textAlign = TextAlign.Center
            )
            Text(
                text = data.bookAuthor,
                fontSize = 20.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = inter(),
                color = Cl.textSearch
            )
            Image(
                painter = rememberAsyncImagePainter(model = data.imgUrl),
                contentDescription = "img",
                modifier = Modifier
                    .padding(top = 22.dp)
                    .width(180.dp)
                    .height(210.dp),

                )

        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomStart)
                .padding(16.dp)
                .padding(top = 36.dp)
        ) {
            Text(
                text = data.bookTitle,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = display()
            )
            Text(
                text = data.description,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = inter(),
                modifier = Modifier.padding(top = 16.dp),
                maxLines = 10
            )

        }

        Card(
            modifier = Modifier
                .fillMaxWidth(0.7f)
                .height(40.dp)
                .align(Alignment.Center)
                .clip(RoundedCornerShape(6.dp))
                .background(color = Cl.btnTextColor)
                .shadow(4.dp),

            ) {
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(
                    modifier = Modifier
                        .background(
                            color = Cl.bgStar,
                            shape = RoundedCornerShape(8.dp)
                        )
                        .padding(horizontal = 4.dp)
                        .padding(vertical = 1.8.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_star),
                        contentDescription = "icon",
                        tint = Cl.starColor
                    )
                    Text(
                        text = data.pp,
                        fontFamily = inter(),
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Normal
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = "Fantasy",
                    modifier = Modifier
                        .clip(RoundedCornerShape(8.dp))
                        .background(color = Cl.bgItem)
                        .padding(horizontal = 8.dp)
                        .padding(vertical = 1.4.dp),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = inter()
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = data.pageCount,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = inter()
                )
                Text(
                    text = "Pages",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Normal,
                    fontFamily = inter(),
                    modifier = Modifier.padding(start = 4.dp)
                )
            }

        }
        val btnText = remember {
            mutableStateOf(uiState.value.btnText)
        }
        Button(
            onClick = {
                btnText.value = uiState.value.btnText
                intent.invoke(
                    if (btnText.value == "Reader") {
                        DownloadContract.Intent.ClickNext
                    } else {
                        DownloadContract.Intent.ClickDownload(data)
                    }
                )
            },
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth(0.5f)
                .padding(bottom = 16.dp),

            colors = ButtonDefaults.buttonColors(
                contentColor = Cl.btnTextColor,
                containerColor = Cl.btnColor
            ),

            shape = RoundedCornerShape(12.dp)
        ) {
            if(uiState.value.loading){
                CircularProgressIndicator(modifier = Modifier.size(32.dp), color = Cl.btnTextColor)
            }else{
                Text(text = uiState.value.btnText)
            }
        }
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(32.dp))
                .border(1.2.dp, color = Cl.textColor, shape = RoundedCornerShape(32.dp))
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    intent.invoke(DownloadContract.Intent.ClickBack)
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "icon"
            )
        }

    }
}
