package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.presentation.other.SetStatusBarColor

class LoginViewModel(private val setStatusBarColor: SetStatusBarColor) : ViewModel() {

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColor.execute(setStatusBarParam)
    }
}