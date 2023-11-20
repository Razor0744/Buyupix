package team.four.mys.presentation.viewmodelsactivity

import android.app.Activity
import androidx.lifecycle.ViewModel
import team.four.mys.domain.usecases.CheckUserUseCase
import team.four.mys.domain.usecases.SetNavigationColorUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase
import team.four.mys.domain.usecases.SetThemeUseCase

class MainViewModel(
    private val setThemeUseCase: SetThemeUseCase,
    private val checkUserUseCase: CheckUserUseCase,
    private val setNavigationColorUseCase: SetNavigationColorUseCase,
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase
) : ViewModel() {

    fun setTheme() {
        setThemeUseCase.execute()
    }

    fun checkUser(): Boolean {
        return checkUserUseCase.execute()
    }

    fun setStatusBarColor(activity: Activity, color: Int) {
        setStatusBarColorUseCase.execute(activity = activity, color = color)
    }

    fun setNavigationColor(activity: Activity, color: Int) {
        setNavigationColorUseCase.execute(activity = activity, color = color)
    }
}