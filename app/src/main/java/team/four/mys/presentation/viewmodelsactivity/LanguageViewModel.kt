package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetStatusBarColorUseCase

class LanguageViewModel(private val setStatusBarColorUseCase: SetStatusBarColorUseCase) : ViewModel() {

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam)
    }
}