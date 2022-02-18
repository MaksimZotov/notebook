package com.maksimzotov.notebook.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val STORAGE_NAME = "SETTINGS_STORAGE"

private val Context.dataStoreSettings: DataStore<Preferences> by preferencesDataStore(STORAGE_NAME)

class SettingsStorage @Inject constructor(private val context: Context) {
    companion object {
        private const val DARK_THEME_KEY = "DARK_THEME_KEY"
        private const val BOTTOM_NAVIGATION_KEY = "BOTTOM_NAVIGATION_KEY"
    }

    private val darkThemeKey = booleanPreferencesKey(DARK_THEME_KEY)
    private val bottomNavigationKey = booleanPreferencesKey(BOTTOM_NAVIGATION_KEY)

    val darkTheme: Flow<Boolean> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[darkThemeKey] ?: false
        }

    val bottomNavigation: Flow<Boolean> = context.dataStoreSettings.data
        .map { preferences ->
            preferences[bottomNavigationKey] ?: true
        }

    suspend fun setDarkTheme(isAble: Boolean) = context.dataStoreSettings.edit { preferences ->
        preferences[darkThemeKey] = isAble
    }

    suspend fun setBottomNavigation(isAble: Boolean) =
        context.dataStoreSettings.edit { preferences ->
            preferences[bottomNavigationKey] = isAble
        }
}