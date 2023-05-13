package com.example.movieapp

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performImeAction
import androidx.compose.ui.test.performScrollToIndex
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.movieapp.data.MovieRepository
import com.example.movieapp.ui.navigation.Screen
import com.example.movieapp.ui.screen.detail.DetailScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MovieAppTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()
    private lateinit var navController: TestNavHostController
    val movielist = FakeDataSource.generateDummyList()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MovieAppTheme() {
                navController = TestNavHostController(LocalContext.current)
                navController.navigatorProvider.addNavigator(ComposeNavigator())
                MovieApp(navController = navController)
            }
        }
        Thread.sleep(2000)
    }

    @Test
    fun navHost_verifyStartDestination() {
        navController.assertCurrentRouteName(Screen.Home.route)
    }

    @Test
    fun navHost_navigateToFavorite() {
        composeTestRule.onNodeWithText("Favorite").performClick()
        navController.assertCurrentRouteName(Screen.Favorite.route)
    }

    @Test
    fun navHost_navigateToAbout() {
        composeTestRule.onNodeWithText("About Page").performClick()
        navController.assertCurrentRouteName(Screen.Profile.route)
    }



}