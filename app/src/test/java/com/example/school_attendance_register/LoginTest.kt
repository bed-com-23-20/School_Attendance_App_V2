package com.example.school_attendance_register

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.*
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.navigation.NavController
import com.example.school_attendance_register.plastol_pages.AuthViewModel
import com.example.school_attendance_register.plastol_pages.LoginPage
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.*

class LoginTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private lateinit var navController: NavController
    private lateinit var authViewModel: AuthViewModel<Any?> // Changed to Any? to match LoginPage

    @Before
    fun setup() {
        // Mock NavController
        navController = mock()

        // Mock AuthViewModel with Any?
        authViewModel = mock()
    }

    @Test
    fun testEmailValidation_showsErrorForInvalidEmail() {
        composeTestRule.setContent {
            LoginPage(navController = navController, viewModel = authViewModel)
        }

        // Enter an invalid email
        composeTestRule.onNodeWithText("Username").performTextInput("invalid-email")

        // Assert that error message is shown
        composeTestRule.onNodeWithText("Please enter a valid email address").assertIsDisplayed()
    }

    @Test
    fun testEmptyFields_showsToastMessage() {
        composeTestRule.setContent {
            LoginPage(navController = navController, viewModel = authViewModel)
        }

        // Click the login button without entering email or password
        composeTestRule.onNodeWithText("Login").performClick()

        // Check if "Logging in..." does not appear, indicating the button click was prevented
        composeTestRule.onNodeWithText("Logging in...").assertDoesNotExist()
    }

    @Test
    fun testValidLogin_navigatesToDashboard() {
        val email = "test@example.com"
        val password = "password123"
        val loginResult = mutableStateOf(Result.success(Pair(email, password)))

        // Mocking ViewModel behavior for successful login
        whenever(authViewModel.fetchUserCredentials(eq(email), any())).thenAnswer { invocation: org.mockito.invocation.InvocationOnMock ->
            val callback = invocation.arguments[1] as (Result<Pair<String, String>>) -> Unit
            callback.invoke(loginResult.value)
        }

        composeTestRule.setContent {
            LoginPage(navController = navController, viewModel = authViewModel)
        }

        // Input valid email and password
        composeTestRule.onNodeWithText("Username").performTextInput(email)
        composeTestRule.onNodeWithText("Password").performTextInput(password)

        // Click the login button
        composeTestRule.onNodeWithText("Login").performClick()

        // Verify navigation to Admin Dashboard
        verify(navController).navigate("Admin_Dash_Board")
    }

    @Test
    fun testInvalidLogin_showsErrorMessage() {
        val email = "test@example.com"
        val password = "wrongPassword"
        val loginResult = mutableStateOf(Result.failure<Pair<String, String>>(Throwable("Incorrect password")))

        // Mock ViewModel behavior for failed login
        whenever(authViewModel.fetchUserCredentials(eq(email), any())).thenAnswer { invocation: org.mockito.invocation.InvocationOnMock ->
            val callback = invocation.arguments[1] as (Result<Pair<String, String>>) -> Unit
            callback.invoke(loginResult.value)
        }

        composeTestRule.setContent {
            LoginPage(navController = navController, viewModel = authViewModel)
        }

        // Input email and incorrect password
        composeTestRule.onNodeWithText("Username").performTextInput(email)
        composeTestRule.onNodeWithText("Password").performTextInput(password)

        // Click the login button
        composeTestRule.onNodeWithText("Login").performClick()

        // Assert that error message is shown
        composeTestRule.onNodeWithText("Incorrect password").assertIsDisplayed()
    }
}
