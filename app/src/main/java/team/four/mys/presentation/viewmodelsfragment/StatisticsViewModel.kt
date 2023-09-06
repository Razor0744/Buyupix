package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetStatusBarColorUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StatisticsViewModel(
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase
) :
    ViewModel() {

    val numberOfSubscriptions = MutableLiveData<Number>()

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam)
    }
}