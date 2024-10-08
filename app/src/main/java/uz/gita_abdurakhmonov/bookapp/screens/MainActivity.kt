package uz.gita_abdurakhmonov.bookapp.screens

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.Navigator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.last
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import uz.gita_abdurakhmonov.bookapp.navigation.NavigationHandler
import uz.gita_abdurakhmonov.bookapp.screens.home.menu.MenuScreen
import uz.gita_abdurakhmonov.bookapp.ui.theme.BookAppTheme
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001

    @Inject
    lateinit var appNavigationHandler: NavigationHandler

    @SuppressLint("CoroutineCreationDuringComposition")

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        super.onCreate(savedInstanceState)

//        enableEdgeToEdge()
        setContent {
            Navigator(screen = MenuScreen) { navigator ->
                appNavigationHandler.screenState.onEach {
                    it.invoke(navigator)
                }.launchIn(lifecycleScope)
                CurrentScreen()
            }
        }
    }
}