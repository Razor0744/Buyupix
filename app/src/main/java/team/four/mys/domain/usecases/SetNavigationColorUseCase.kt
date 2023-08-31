package team.four.mys.domain.usecases

import team.four.mys.domain.models.SetNavigationColorParam

class SetNavigationColorUseCase {

    fun execute(setNavigationColorParam: SetNavigationColorParam) {
        setNavigationColorParam.activity.window.navigationBarColor = setNavigationColorParam.color
    }
}