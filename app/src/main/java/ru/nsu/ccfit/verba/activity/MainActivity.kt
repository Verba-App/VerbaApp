package ru.nsu.ccfit.verba.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import ru.nsu.ccfit.verba.core.designsystem.VerbaAppAndroidTheme
import ru.nsu.ccfit.verba.navigation.BaseViewModelFactoryProvider
import ru.nsu.ccfit.verba.navigation.TemplateNaveHost

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @EntryPoint
    @InstallIn(ActivityComponent::class)
    interface ViewModelFactoryProvider : BaseViewModelFactoryProvider


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
          VerbaAppAndroidTheme {
               TemplateNaveHost()
          }
        }
    }
}