package ru.nsu.ccfit.verba.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.android.AndroidEntryPoint
import ru.nsu.ccfit.verba.core.designsystem.VerbaAppAndroidTheme
import ru.nsu.ccfit.verba.navigation.TemplateNaveHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
          VerbaAppAndroidTheme {
               TemplateNaveHost()
          }
        }
    }
}