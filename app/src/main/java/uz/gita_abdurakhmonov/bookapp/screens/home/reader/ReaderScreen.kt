package uz.gita_abdurakhmonov.bookapp.screens.home.reader

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.rizzi.bouquet.ResourceType
import com.rizzi.bouquet.VerticalPDFReader
import com.rizzi.bouquet.rememberHorizontalPdfReaderState
import com.rizzi.bouquet.rememberVerticalPdfReaderState
import okhttp3.internal.wait
import uz.gita_abdurakhmonov.bookapp.R
import uz.gita_abdurakhmonov.bookapp.ui.theme.Cl
import java.io.File

data class ReaderScreen(private val url: String) : Screen {
    @Composable
    override fun Content() {
        val viewModel: ReaderContract.ViewModel = getViewModel<ReaderViewModel>()
        Log.d("AAA", "Content: $url")
        ReaderScreenContent(url, viewModel::onEventDispatchers)
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun ReaderScreenContent(
    url: String,
    intent: () -> Unit
) {
    Log.d("AAA", "ReaderScreenContent: $url")
    val pdfState = rememberVerticalPdfReaderState(
        resource = ResourceType.Remote(url),
        isZoomEnable = true
    )
    Box(modifier = Modifier.fillMaxSize()) {
            VerticalPDFReader(
                state = pdfState,
                modifier = Modifier
                    .fillMaxSize()
                    .background(color = Color.White)
            )


        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(32.dp))
                .border(1.2.dp, color = Cl.textColor, shape = RoundedCornerShape(32.dp))
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    intent.invoke()
                },
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = "icon"
            )

        }
        if (!pdfState.isLoaded) {
            CircularProgressIndicator(
                modifier = Modifier
                    .size(60.dp)
                    .align(Alignment.Center),
                color = Cl.starColor
            )
        }

    }


}