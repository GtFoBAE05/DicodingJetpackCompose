package com.example.movieapp.ui.screen.detail

import androidx.activity.ComponentActivity
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import com.example.movieapp.MovieApp
import com.example.movieapp.data.remote.response.Genre
import com.example.movieapp.ui.theme.MovieAppTheme
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DetailScreenKtTest{
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()

    val genre = Genre(1, "action")
    val genresList = listOf<Genre>(genre)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            MovieAppTheme() {
                DetailContent(
                    isFavorite = false,
                    imageUrl = "image",
                    title = "title",
                    rating = "rating",
                    releaseDate = "releasedate",
                    genres = genresList,
                    overview = "overview",
                    onClick = {  })
            }
        }
        Thread.sleep(2000)
    }

    @Test
    fun check_detail_screen_tag() {
        composeTestRule.onNodeWithTag("Image").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Title").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Rating").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Release|Genre").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Summary").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Overview").assertIsDisplayed()
    }

    @Test
    fun check_detail_screen_fav_btn() {
        composeTestRule.onNodeWithTag("favoriteBtn").assertIsEnabled()
    }
}