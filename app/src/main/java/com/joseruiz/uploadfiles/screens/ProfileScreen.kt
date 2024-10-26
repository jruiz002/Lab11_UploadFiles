package com.joseruiz.uploadfiles.screens

import android.content.Context
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavController
import com.joseruiz.uploadfiles.Screen
import com.joseruiz.uploadfiles.dataStore.getUserProfile
import com.joseruiz.uploadfiles.dataStore.saveUserProfile
import kotlinx.coroutines.launch


@Composable
fun UserProfileScreen(context: Context, navController: NavController) {
    var firstName by remember { mutableStateOf(TextFieldValue("")) }
    var lastName by remember { mutableStateOf(TextFieldValue("")) }
    var email by remember { mutableStateOf(TextFieldValue("")) }
    var birthDate by remember { mutableStateOf(TextFieldValue("")) }
    val scope = rememberCoroutineScope()

    // Cargar los datos de usuario al iniciar la pantalla
    LaunchedEffect(Unit) {
        getUserProfile(context).collect { userProfile ->
            firstName = TextFieldValue(userProfile.firstName)
            lastName = TextFieldValue(userProfile.lastName)
            email = TextFieldValue(userProfile.email)
            birthDate = TextFieldValue(userProfile.birthDate)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Text(
            text = "User Profile",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        OutlinedTextField(
            value = firstName,
            onValueChange = { firstName = it },
            label = { Text("First Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = lastName,
            onValueChange = { lastName = it },
            label = { Text("Last Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("Email Address") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        OutlinedTextField(
            value = birthDate,
            onValueChange = { birthDate = it },
            label = { Text("Date of Birth") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                scope.launch {
                    saveUserProfile(
                        context = context,
                        firstName = firstName.text,
                        lastName = lastName.text,
                        email = email.text,
                        birthDate = birthDate.text
                    )
                }
                navController.navigate(Screen.Home.route)
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Save")
        }
    }
}