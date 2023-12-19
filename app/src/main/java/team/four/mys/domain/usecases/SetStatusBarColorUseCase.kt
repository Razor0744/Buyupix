package team.four.mys.domain.usecases

import android.app.Activity
import android.content.res.Configuration
import androidx.core.view.WindowInsetsControllerCompat
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
                    Configuration.UI_MODE_NIGHT_YES -> {
                        WindowInsetsControllerCompat(
                            activity.window,
                            activity.window.decorView
                        ).isAppearanceLightStatusBars = false
                    }

                    Configuration.UI_MODE_NIGHT_NO -> {
                        WindowInsetsControllerCompat(
                            activity.window,
                            activity.window.decorView
                        ).isAppearanceLightStatusBars = true
                    }
                }
            }

            "Dark Theme"
            -> {
                WindowInsetsControllerCompat(
                    activity.window,
                    activity.window.decorView
                ).isAppearanceLightStatusBars = false
            }

            "Light Theme" -> {
                WindowInsetsControllerCompat(
                    activity.window,
                    activity.window.decorView
                ).isAppearanceLightStatusBars = true
            }

            else -> {
                when (activity.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> {
                        WindowInsetsControllerCompat(
                            activity.window,
                            activity.window.decorView
                        ).isAppearanceLightStatusBars = false
                    }

                    Configuration.UI_MODE_NIGHT_NO -> {
                        WindowInsetsControllerCompat(
                            activity.window,
                            activity.window.decorView
                        ).isAppearanceLightStatusBars = true
                    }
                }
            }
        }
    }
}