package nl.hva.madlevel6task2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import nl.hva.madlevel6task2.ui.screens.GameScreens
import nl.hva.madlevel6task2.ui.screens.HistoryScreen
import nl.hva.madlevel6task2.ui.screens.PlayScreen
import nl.hva.madlevel6task2.ui.theme.MadLevel6Task2Theme
import nl.hva.madlevel6task2.viewModel.GameViewModel as GameViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MadLevel6Task2Theme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    GameApp(viewModel())
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GameApp(viewModel : GameViewModel) {
    //for navigation
    val navController = rememberNavController()
    val isHistoryScreen = remember { mutableStateOf(false) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        color = Color.White
                    )
                },
                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Blue)
            )
        },
        floatingActionButton = {
            if (isHistoryScreen.value) {
                FloatingActionButton(
                    onClick = {
                        viewModel.deleteAll()
                    },
                    modifier = Modifier.padding(16.dp),
                    containerColor = Color.Red,
                ) {
                    Icon(Icons.Default.Delete, "Delete history", tint = Color.White)
                }
            }

        },
        content = { innerPadding ->
            GameNavHost(Modifier.padding(innerPadding), navController, viewModel())
        },
        bottomBar = {
            GameBottomNav(navController)
        }
    )
    // Check the current destination and update the isHistoryScreen boolean
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    isHistoryScreen.value = currentDestination?.route == GameScreens.HistoryScreen.route
}

@Composable
fun GameNavHost(modifier : Modifier, navController : NavHostController, viewModel : GameViewModel) {
    NavHost(
        navController = navController,
        startDestination = GameScreens.PlayScreen.route,
        modifier = modifier
    ) {
        composable(GameScreens.PlayScreen.route) {
            PlayScreen(
                modifier = Modifier,
                navController,
                viewModel = viewModel
            )
        }
        composable(GameScreens.HistoryScreen.route) {
            HistoryScreen(
                modifier = Modifier,
                viewModel
            )
        }
    }
}

@Composable
fun GameBottomNav(navController : NavController) {
    BottomNavigation {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentDestination = navBackStackEntry?.destination
        val items = listOf(
            GameScreens.PlayScreen,
            GameScreens.HistoryScreen
        )
        items.forEach { screen ->
            BottomNavigationItem(
                icon = {
                    if (screen == GameScreens.PlayScreen) {
                        Icon(Icons.Default.PlayArrow, contentDescription = null, tint = Color.White)
                    } else if (screen == GameScreens.HistoryScreen) {
                        Icon(Icons.Default.List, contentDescription = null, tint = Color.White)
                    }

                },
                label = { Text(stringResource(screen.resourceId), color = Color.White) },
                selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    navController.navigate(screen.route) {
                        // Pop up to the start destination of the graph to
                        // avoid building up a large stack of destinations
                        // on the back stack as users select items
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        // Avoid multiple copies of the same destination when
                        // reselecting the same item
                        launchSingleTop = true
                        // Restore state when reselecting a previously selected item
                        restoreState = true
                    }
                }
            )
        }
    }
}


