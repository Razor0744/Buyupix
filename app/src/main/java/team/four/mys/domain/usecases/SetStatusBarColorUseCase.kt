package team.four.mys.domain.usecases

import android.app.Activity
import android.content.res.Configuration
import android.view.View
import team.four.mys.domain.models.SettingsPreferencesParam
import javax.inject.Inject

class SetStatusBarColorUseCase @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase
) {

    fun execute(activity: Activity, color: Int) {
        activity.window.statusBarColor = color
        when (getSettingsUseCase.execute(SettingsPreferencesParam(key = "DarkMode")).value) {
            "System Theme" -> {
                when (activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

                    Configuration.UI_MODE_NIGHT_NO -> activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
            }

            "Dark Theme"
            -> activity.window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

            "Light Theme" -> activity.window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

            else -> {
                when (activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

                    Configuration.UI_MODE_NIGHT_NO -> activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
            }
        }
    }
}