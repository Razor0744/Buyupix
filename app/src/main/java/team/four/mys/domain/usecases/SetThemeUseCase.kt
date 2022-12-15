package team.four.mys.domain.usecases

import androidx.appcompat.app.AppCompatDelegate
import team.four.mys.data.db.Preferences
import team.four.mys.data.storage.SettingsPreferences

class SetThemeUseCase {

    fun execute() {
        when (Preferences.getSettings("DarkMode")) {
            "System Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "Dark Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "Light Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}