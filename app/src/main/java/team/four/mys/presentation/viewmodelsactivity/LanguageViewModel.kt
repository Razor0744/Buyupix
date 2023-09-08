package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase

class LanguageViewModel(
    private val getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    fun getSettings(settingsPreferencesParam: SettingsPreferencesParam): String? {
        return getSettingsUseCase.execute(settingsPreferencesParam = settingsPreferencesParam).value
    }
}