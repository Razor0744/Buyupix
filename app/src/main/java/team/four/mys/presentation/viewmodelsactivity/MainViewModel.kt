package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.usecases.SetThemeUseCase

class MainViewModel(
    private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {

    fun setTheme(){
        setThemeUseCase.execute()
    }

}