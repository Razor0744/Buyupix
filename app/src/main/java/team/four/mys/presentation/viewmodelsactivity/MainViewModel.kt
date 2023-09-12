package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.usecases.CheckUserUseCase
import team.four.mys.domain.usecases.SetThemeUseCase

class MainViewModel(
    private val setThemeUseCase: SetThemeUseCase,
    private val checkUserUseCase: CheckUserUseCase
) : ViewModel() {

    fun setTheme(){
        setThemeUseCase.execute()
    }

    fun checkUser(): Boolean{
        return checkUserUseCase.execute()
    }

}