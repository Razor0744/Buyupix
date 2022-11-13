package team.four.buyupix.domain.usecases

import android.content.res.Configuration
import android.view.View
import team.four.buyupix.data.db.Preferences
import team.four.buyupix.domain.models.SetStatusBarParam

class SetStatusBarUseCase {

    fun setStatusBar(param: SetStatusBarParam) {
        param.activity.window.statusBarColor = param.color
        when (Preferences.getSettings("DarkMode")) {
            "System Theme" -> {
                when (param.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
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
                when (param.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
                    Configuration.UI_MODE_NIGHT_YES -> param.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
                    Configuration.UI_MODE_NIGHT_NO -> param.activity.window.decorView.systemUiVisibility =
                        (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)
                }
            }
        }
    }
}