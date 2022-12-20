package team.four.mys.presentation.other

import android.content.res.Configuration
import android.view.View
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.GetSettingsUseCase

class SetStatusBarColor(
    private val getSettingsUseCase: GetSettingsUseCase
) {

    fun execute(param: SetStatusBarParam) {
        param.activity.window.statusBarColor = param.color
        when (getSettingsUseCase.execute(SettingsPreferencesParam(key = "DarkMode")).value) {
            "System Theme" -> {
                when (param.activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> param.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                    Configuration.UI_MODE_NIGHT_NO -> param.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
            }
            "Dark Theme"
            -> param.activity.window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            "Light Theme" -> param.activity.window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            else -> {
                when (param.activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> param.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                    Configuration.UI_MODE_NIGHT_NO -> param.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
            }
        }
    }
}