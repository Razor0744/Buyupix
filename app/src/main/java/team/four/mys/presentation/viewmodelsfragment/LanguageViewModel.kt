package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import javax.inject.Inject

class LanguageViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase
) : ViewModel() {

    fun getSettings(settingsPreferencesParam: SettingsPreferencesParam): String? {
        return getSettingsUseCase.execute(settingsPreferencesParam = settingsPreferencesParam).value
    }
}