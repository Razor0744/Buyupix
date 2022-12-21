package team.four.mys.presentation.other

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase

class SetTheme(context: Context) {

    private val settingsStorage by lazy { SettingsPreferences(context = context) }
    private val settingsRepository by lazy { SettingsRepositoryImpl(settingsStorage) }
    private val getSettingsUseCase by lazy { GetSettingsUseCase(settingsRepository) }

    fun execute() {
        when (getSettingsUseCase.execute(SettingsPreferencesParam(key = "DarkMode")).value) {
            "System Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "Dark Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "Light Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}