package ru.nsu.ccfit.verbaapp.ui.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import com.ramcosta.composedestinations.DestinationsNavHost
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.ccfit.verbaapp.ui.screen.NavGraphs
import ru.nsu.ccfit.verbaapp.ui.theme.VerbaAppAndroidTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // supportActionBar?.hide() // Hide the action bar
        WindowCompat.setDecorFitsSystemWindows(window, false) // Enable full screen mode
        setContent {
            VerbaAppAndroidTheme{
                DestinationsNavHost(navGraph = NavGraphs.root)
            }
        }
    }
}