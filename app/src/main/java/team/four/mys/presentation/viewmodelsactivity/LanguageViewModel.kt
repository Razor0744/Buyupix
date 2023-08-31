package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase

class LanguageViewModel(
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase,
    private val getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam)
    }

    fun getSettings(settingsPreferencesParam: SettingsPreferencesParam): String? {
        return getSettingsUseCase.execute(settingsPreferencesParam = settingsPreferencesParam).value
    }
}