package com.example.movieapp.ui.screen.profile

import androidx.activity.ComponentActivity
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.example.movieapp.ui.theme.MovieAppTheme
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ProfileScreenKtTest {
    @get:Rule
    val composeTestRule = createAndroidComposeRule<ComponentActivity>()


    @Before
    fun setUp() {
        composeTestRule.setContent {
            MovieAppTheme {
                ProfileScreen()
            }
        }
        Thread.sleep(2000)
    }

    @Test
    fun check_detail_screen_tag() {
        composeTestRule.onNodeWithTag("Image").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Name").assertIsDisplayed()
        composeTestRule.onNodeWithTag("Email").assertIsDisplayed()
    }
}