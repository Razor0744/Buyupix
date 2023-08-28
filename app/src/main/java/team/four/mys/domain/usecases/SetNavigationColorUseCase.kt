package team.four.mys.domain.usecases

import team.four.mys.domain.models.SetNavigationBarParam

class SetNavigationColorUseCase {

    fun execute(param: SetNavigationBarParam) {
        param.activity.window.navigationBarColor = param.color
    }
}