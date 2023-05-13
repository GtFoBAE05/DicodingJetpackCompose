package com.example.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.movieapp.ui.components.BottomBarComponents
import com.example.movieapp.ui.navigation.Screen
import com.example.movieapp.ui.screen.detail.DetailScreen
import com.example.movieapp.ui.screen.favorite.FavoriteScreen
import com.example.movieapp.ui.screen.home.HomeScreen
import com.example.movieapp.ui.screen.profile.ProfileScreen
import com.example.movieapp.ui.screen.search.SearchScreen
import com.example.movieapp.ui.theme.MovieAppTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MovieAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MovieApp()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MovieApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val excludedRoutes = listOf(Screen.Detail.route, Screen.Search.route)
    Scaffold(
        bottomBar = {
            if (currentRoute !in excludedRoutes) {
                BottomBarComponents(navController = navController)
            }
        },
        floatingActionButton = {
            if (currentRoute !in excludedRoutes) {
                FloatingActionButton(
                    onClick = { navController.navigate(Screen.Search.route) },
                    containerColor = MaterialTheme.colorScheme.primary,
                    modifier = modifier,
                ) {
                    Icon(Icons.Default.Search, contentDescription = stringResource(R.string.search_page))
                }
            }

        },
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(
                    modifier = modifier,
                    navigateToDetail = {
                        navController.navigate(Screen.Detail.createRoute(it))
                    }
                )
            }
            composable(Screen.Favorite.route) {
                FavoriteScreen(
                    modifier = modifier,
                    navigateToDetail = {
                        navController.navigate(Screen.Detail.createRoute(it))
                    }
                )
            }
            composable(Screen.Profile.route) {
                ProfileScreen(
                    modifier = modifier,
                )
            }
            composable(
                route = Screen.Detail.route,
                arguments = listOf(navArgument("movieId") { type = NavType.LongType })
            ) {
                val id = it.arguments?.getLong("movieId") ?: -1L
                DetailScreen(
                    movieId = id,
                )
            }
            composable(Screen.Search.route) {
                SearchScreen(
                    modifier = modifier,
                    navigateToDetail = {
                        navController.navigate(Screen.Detail.createRoute(it))
                    })
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun MovieAppPreview() {
    MovieAppTheme {
        MovieApp()
    }
}