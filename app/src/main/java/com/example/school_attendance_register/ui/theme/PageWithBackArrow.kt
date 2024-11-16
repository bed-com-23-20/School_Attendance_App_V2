package com.example.school_attendance_register.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun PageWithBackArrow(
    navController: NavController,
    title: String,
    content: @Composable (Modifier) -> Unit
) {
    Scaffold(
        topBar = { TopAppBarWithBack(navController = navController, title = title) }
    ) { paddingValues ->
        content(Modifier.padding(paddingValues))
    }
}
