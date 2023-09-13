package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class StatisticsViewModel(
) :
    ViewModel() {

    val numberOfSubscriptions = MutableLiveData<Number>()

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }
}