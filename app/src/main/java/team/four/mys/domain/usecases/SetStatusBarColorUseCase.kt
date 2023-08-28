package team.four.mys.domain.usecases

import android.content.res.Configuration
import android.view.View
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SettingsPreferencesParam

class SetStatusBarColorUseCase(
    private val getSettingsUseCase: GetSettingsUseCase
) {

    fun execute(setStatusBarParam: SetStatusBarParam) {
        setStatusBarParam.activity.window.statusBarColor = setStatusBarParam.color
        when (getSettingsUseCase.execute(SettingsPreferencesParam(key = "DarkMode")).value) {
            "System Theme" -> {
                when (setStatusBarParam.activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> setStatusBarParam.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                    Configuration.UI_MODE_NIGHT_NO -> setStatusBarParam.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
            }
            "Dark Theme"
            -> setStatusBarParam.activity.window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            "Light Theme" -> setStatusBarParam.activity.window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
            else -> {
                when (setStatusBarParam.activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> setStatusBarParam.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                    Configuration.UI_MODE_NIGHT_NO -> setStatusBarParam.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
            }
        }
    }
}