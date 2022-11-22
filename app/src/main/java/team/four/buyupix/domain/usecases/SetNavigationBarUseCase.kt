package team.four.buyupix.domain.usecases

import team.four.buyupix.domain.models.SetNavigationBarParam

class SetNavigationBarUseCase {

    fun execute(param: SetNavigationBarParam) {
        param.activity.window.navigationBarColor = param.color
    }
}