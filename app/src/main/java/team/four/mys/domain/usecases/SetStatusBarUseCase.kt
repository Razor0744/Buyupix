package team.four.mys.domain.usecases

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.view.View
import team.four.mys.R
import team.four.mys.data.db.Preferences

class SetStatusBarUseCase {

    fun execute(context: Context, activity: Activity) {
        activity.window.statusBarColor =
            context.getColor(R.color.backgroundNavBar)
        when (Preferences.getSettings("DarkMode")) {
            "System Theme" -> {
                when (context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK) {
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
        }
    }
}