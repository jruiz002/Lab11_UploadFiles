package com.joseruiz.uploadfiles.dataStore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

// DataStore Singleton para evitar múltiples instancias
val Context.dataStore by preferencesDataStore(name = "user_profile")

// Claves para almacenar datos
object UserProfileKeys {
    val FIRST_NAME = stringPreferencesKey("first_name")
    val LAST_NAME = stringPreferencesKey("last_name")
    val EMAIL = stringPreferencesKey("email")
    val BIRTH_DATE = stringPreferencesKey("birth_date")
}

// Función para guardar datos en DataStore
suspend fun saveUserProfile(
    context: Context,
    firstName: String,
    lastName: String,
    email: String,
    birthDate: String
) {
    context.dataStore.edit { preferences ->
        preferences[UserProfileKeys.FIRST_NAME] = firstName
        preferences[UserProfileKeys.LAST_NAME] = lastName
        preferences[UserProfileKeys.EMAIL] = email
        preferences[UserProfileKeys.BIRTH_DATE] = birthDate
    }
}

// Función para cargar datos desde DataStore
fun getUserProfile(context: Context): Flow<UserProfile> {
    return context.dataStore.data.map { preferences ->
        UserProfile(
            firstName = preferences[UserProfileKeys.FIRST_NAME] ?: "",
            lastName = preferences[UserProfileKeys.LAST_NAME] ?: "",
            email = preferences[UserProfileKeys.EMAIL] ?: "",
            birthDate = preferences[UserProfileKeys.BIRTH_DATE] ?: ""
        )
    }
}

// Clase de datos para representar el perfil del usuario
data class UserProfile(
    val firstName: String,
    val lastName: String,
    val email: String,
    val birthDate: String
)