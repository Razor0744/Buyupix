package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetNavigationColorParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetNavigationColorUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase
import team.four.mys.domain.usecases.SetThemeUseCase

class FirstActivityViewModel(
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase,
    private val setThemeUseCase: SetThemeUseCase,
    private val setNavigationColorUseCase: SetNavigationColorUseCase
) : ViewModel() {

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam)
    }

    fun setTheme() {
        setThemeUseCase.execute()
    }

    fun setNavigationColor(setNavigationColorParam: SetNavigationColorParam) {
        setNavigationColorUseCase.execute(setNavigationColorParam = setNavigationColorParam)
    }

}