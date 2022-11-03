package team.four.mys.domain.usecases

import team.four.mys.domain.models.SetNavigationBarParam

class SetNavigationBarUseCase {

    fun setNavigationBar(param: SetNavigationBarParam) {
        param.activity.window.navigationBarColor = param.color
    }
}