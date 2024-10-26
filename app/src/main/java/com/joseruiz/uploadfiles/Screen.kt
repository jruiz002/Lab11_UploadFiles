package com.joseruiz.uploadfiles

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

sealed class Screen (val route: String, val title: String, val icon: ImageVector) {
    object Home : Screen("home-screen", "Save Data", Icons.Filled.AddCircle)
    object Profile : Screen("profile-screen", "Show Data", Icons.Filled.Person)
    object User : Screen("user-screen", "Profile", Icons.Filled.AccountCircle)
}