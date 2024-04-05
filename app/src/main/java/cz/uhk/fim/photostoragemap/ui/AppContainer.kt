package cz.uhk.fim.photostoragemap.ui

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import cz.uhk.fim.photostoragemap.ui.home.HomeScreen

@Composable
fun AppContainer() {
    val controller = rememberNavController()
    NavHost(
        navController = controller,
        startDestination = DestinationHome,
    ) {
        composable(DestinationHome) {
            HomeScreen()
        }
    }
}

private const val DestinationHome = "home"