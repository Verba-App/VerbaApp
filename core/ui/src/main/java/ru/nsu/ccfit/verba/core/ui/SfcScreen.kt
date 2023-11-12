package ru.nsu.ccfit.verba.core.ui

import androidx.compose.runtime.Composable
import ru.nsu.ccfit.verba.core.common.VerbaOnEventListener

@Composable
fun VerbaScreen(viewModel: VerbaOnEventListener, content: @Composable () -> Unit) {

    content.invoke()

    innerContent(viewModel)
}

@Composable
private fun innerContent(viewModel: VerbaOnEventListener) {

//    val context = LocalContext.current
//     LaunchedEffect(viewModel) {
//       viewModel.events.collect { event ->
//           // Handle the event here
//           print("dd")
//       }
//   }
}
