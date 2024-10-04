package uz.gita_abdurakhmonov.bookapp.screens.home.info

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info
import uz.gita_abdurakhmonov.bookapp.R
import uz.gita_abdurakhmonov.bookapp.ui.theme.Cl
import uz.gita_abdurakhmonov.bookapp.ui.theme.display
import uz.gita_abdurakhmonov.bookapp.ui.theme.inter

object InfoScreen : Screen {
    private fun readResolve(): Any = InfoScreen

    @Composable
    override fun Content() {
        val viewModel:InfoContract.ViewModel = getViewModel<InfoViewModel>()
        InfoScreenContent(viewModel::onEventDispatchers)
    }
}

@SuppressLint("UnrememberedMutableInteractionSource")
@Composable
fun InfoScreenContent(
    intent:()->Unit
) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Box(
            modifier = Modifier
                .padding(16.dp)
                .size(32.dp)
                .clip(RoundedCornerShape(32.dp))
                .border(1.2.dp, color = Cl.info, shape = RoundedCornerShape(32.dp))
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
        Text(
            text = "My Books",
            fontSize = 28.sp,
            fontWeight = FontWeight.W800,
            fontFamily = display(),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            color = Cl.info,
        )
        Text(
            text = "Bu dastur orqali siz qiziqarli kitoplarni yuklab olishingiz mumkin xamda o'zingizga yoqgan kitobni mutola qilishingiz mumkin juda qulay interface va kerakli kitoplar to'plami bor dasturning texnik tarafari jetpack Compose da qildim va menga juda katta skill bo'ldi va buni davom ettiraman!",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(16.dp)
                .align(Alignment.CenterHorizontally),
            color = Cl.info,
        )
        Text(
            text = "✅ Coroutine / flow / CallbackFlow",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier.padding(16.dp),
            color = Cl.info,
        )
        Text(
            text = "✅ MVI Orbit",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp),
            color = Cl.info,
        )
        Text(
            text = "✅ Jetpack Compose",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            color = Cl.info,
        )

        Text(
            text = "✅ Voyager",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            color = Cl.info,
        )

        Text(
            text = "✅ Firebase",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            color = Cl.info,
        )
        Text(
            text = "✅ FireStorage",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp),
            color = Cl.info,
        )
        Text(
            text = "Biz bilan bo'glanish",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 40.dp),
            color = Cl.info,
        )
        val localContext = LocalContext.current
        Text(
            text = "Gita dasturchilar akademiyasi",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    val url = "https://t.me/gitauz"
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse(url))
                    startActivity(localContext, i, null)
                },
            color = Cl.info,
            textDecoration = TextDecoration.Underline
        )

        Text(
            text = "Developer:Abdurahmonov.Sardorbek",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 16.dp)
                .clickable(interactionSource = MutableInteractionSource(), indication = null) {
                    val url = "https://t.me/Abdurakhmonov_sardorbek"
                    val i = Intent(Intent.ACTION_VIEW)
                    i.setData(Uri.parse(url))
                    startActivity(localContext, i, null)
                },
            color = Cl.info,
            textDecoration = TextDecoration.Underline
        )

        Text(
            text = "Etiboringiz uchun raxmat!",
            fontSize = 20.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = inter(),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 40.dp)
                .align(Alignment.CenterHorizontally),
            color = Cl.info,
        )

    }

}



@Preview(showBackground = true)
@Composable
fun InfoScreenPreview() {
    InfoScreenContent({})
}