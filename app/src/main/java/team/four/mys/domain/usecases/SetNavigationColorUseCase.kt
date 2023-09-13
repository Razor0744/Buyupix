package team.four.mys.domain.usecases

import android.app.Activity

class SetNavigationColorUseCase {

    fun execute(activity: Activity, color: Int) {
        activity.window.navigationBarColor = color
    }
}