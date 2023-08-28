package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase

class AlertViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setSettingsUseCase: SetSettingsUseCase,
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase
) : ViewModel() {

    fun getSettings(): String? {
        return getSettingsUseCase.execute(SettingsPreferencesParam(key = "Alert")).value
    }

    fun setSettings(value: String?) {
        setSettingsUseCase.execute(SettingsPreferencesParam(key = "Alert", value = value))
    }

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam){
        setStatusBarColorUseCase.execute(setStatusBarParam)
    }
}