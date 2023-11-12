package ru.nsu.ccfit.verba.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.nsu.ccfit.verba.feature.auth.AuthScreen

@Composable
fun TemplateNaveHost(
    navController: NavHostController = rememberNavController(),
) {
    NavHost(navController = navController, startDestination = NavRoutes.Auth.path) {
        composable(NavRoutes.Auth.path) {
            AuthScreen(hiltViewModel(), successAuth = {
              //  navController.navigate(NavRoutes.Details.build(it))
            })
        }
    }
}
