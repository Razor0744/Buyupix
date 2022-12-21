package team.four.mys.presentation.other

import team.four.mys.domain.models.SetNavigationBarParam

class SetNavigationColor {

    fun execute(param: SetNavigationBarParam) {
        param.activity.window.navigationBarColor = param.color
    }
}