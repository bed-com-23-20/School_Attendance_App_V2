package com.example.school_attendance_register.plastol_pages

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import org.junit.Rule
import org.junit.Test


//class LoginPageTest {
//
//    @get:Rule
//    val composeTestRule = createComposeRule()
//
//    @Test
//    fun testLoginPage_displaysCorrectUI() {
//        // Set up the composable to test
//        composeTestRule.setContent {
//            LoginPage(navController = FakeNavController(), viewModel = FakeAuthViewModel())
//        }
//
//        // Assert that the title is displayed
//        composeTestRule.onNodeWithText("ADMIN LOGIN PAGE").assertExists()
//
//        // Simulate typing into the username field
//        composeTestRule.onNodeWithText("Username").performTextInput("test@example.com")
//
//        // Simulate typing into the password field
//        composeTestRule.onNodeWithText("Password").performTextInput("password123")
//
//        // Simulate clicking the Login button
//        composeTestRule.onNodeWithText("Login").performClick()
//
//        // Assert that the login button shows a loading state or that the navigation occurs (depending on your logic)
//        composeTestRule.onNodeWithText("Logging in...").assertIsDisplayed()
//    }
//}
