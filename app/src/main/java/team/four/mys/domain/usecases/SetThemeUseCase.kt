package team.four.mys.domain.usecases

import androidx.appcompat.app.AppCompatDelegate
import team.four.mys.domain.models.SettingsPreferencesParam
import javax.inject.Inject

class SetThemeUseCase @Inject constructor(private val getSettingsUseCase: GetSettingsUseCase) {

    fun execute() {
        when (getSettingsUseCase.execute(SettingsPreferencesParam(key = "DarkMode")).value) {
            "System Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "Dark Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "Light Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}