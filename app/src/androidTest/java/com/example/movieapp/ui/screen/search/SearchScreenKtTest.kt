package com.example.movieapp.ui.screen.search

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import androidx.compose.ui.test.performTextInput
import androidx.navigation.testing.TestNavHostController
import com.example.movieapp.assertCurrentRouteName
import com.example.movieapp.ui.navigation.Screen
import com.example.movieapp.ui.screen.profile.ProfileScreen
import com.example.movieapp.ui.theme.MovieAppTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SearchScreenKtTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MovieAppTheme {
                SearchScreen(navigateToDetail = {})
            }
        }
        Thread.sleep(2000)
    }

    @Test
    fun check_search_bar() {
        composeTestRule.onNodeWithTag("searchbar").assertIsDisplayed()
        composeTestRule.onNodeWithTag("searchbar").performTextInput("boruto")
        composeTestRule.runOnIdle {
            Thread.sleep(3000)
        }
        composeTestRule.onNodeWithTag("ListMovieItems").assertIsDisplayed()
        composeTestRule.onNodeWithTag("ListMovieItems").performScrollToIndex(0).performClick()

    }
}