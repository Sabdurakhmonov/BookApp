package uz.gita_abdurakhmonov.bookapp.screens.home.reader

import android.annotation.SuppressLint
import android.net.Uri
import android.os.ParcelFileDescriptor
import android.util.Log
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
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.rizzi.bouquet.ResourceType
import uz.gita_abdurakhmonov.bookapp.R
import uz.gita_abdurakhmonov.bookapp.ui.theme.Cl
data class ReaderScreen(private val url:String):Screen {
    @Composable
    override fun Content() {
        val viewModel:ReaderContract.ViewModel = getViewModel<ReaderViewModel>()
        Log.d("AAA", "Content: $url")
        ReaderScreenContent(url,viewModel::onEventDispatchers)
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun ReaderScreenContent(
    url: String,
    intent:()->Unit
){
    Box(modifier = Modifier.fillMaxSize()){
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
        ResourceType.Remote(
            url ="https://firebasestorage.googleapis.com/v0/b/bookapp-fd7a6.appspot.com/o/book9.pdf?alt=media&token=ed23fab3-1ba6-4d5b-9c60-bf5024230234",
        )
    }


}