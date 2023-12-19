package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase
import javax.inject.Inject

class AlertViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase
) : ViewModel() {

    fun getSettings(): String? {
        return getSettingsUseCase.execute(SettingsPreferencesParam(key = "Alert")).value
    }

    fun setSettings(value: String?) {
        setSettingsUseCase.execute(SettingsPreferencesParam(key = "Alert", value = value))
    }
}