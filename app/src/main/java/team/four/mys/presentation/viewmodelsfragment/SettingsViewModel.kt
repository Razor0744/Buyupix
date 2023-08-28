package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetStatusBarColorUseCase

class SettingsViewModel(private val setStatusBarColorUseCase: SetStatusBarColorUseCase) : ViewModel() {

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam)
    }
}