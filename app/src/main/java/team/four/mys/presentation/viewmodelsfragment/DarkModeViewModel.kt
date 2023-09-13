package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase

class DarkModeViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase
) : ViewModel() {

    fun getSettings(settingsPreferencesParam: SettingsPreferencesParam): SettingsPreferencesParam {
        return getSettingsUseCase.execute(settingsPreferencesParam = settingsPreferencesParam)
    }

    fun setSettings(settingsPreferencesParam: SettingsPreferencesParam) {
        setSettingsUseCase.execute(settingsPreferencesParam = settingsPreferencesParam)
    }
}