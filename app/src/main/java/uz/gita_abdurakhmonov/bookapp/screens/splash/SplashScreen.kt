package uz.gita_abdurakhmonov.bookapp.screens.splash

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.hilt.getViewModel
import uz.gita_abdurakhmonov.bookapp.ui.theme.display

object SplashScreen:Screen {
    private fun readResolve(): Any = SplashScreen

    @Composable
    override fun Content() {
        val viewModel:SplashContract.ViewModel = getViewModel<SplashViewModel>()
        
    }
}
@Composable
fun SplashScreenContent(){
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center){
        Text(text = "My Books", fontSize = 40.sp, fontWeight = FontWeight.Medium, fontFamily = display(),)
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview(){
    SplashScreenContent()
}