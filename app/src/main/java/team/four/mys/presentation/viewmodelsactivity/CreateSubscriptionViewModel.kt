package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.four.mys.data.api.retrofit.currencies.Retrofit
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Subscription
import team.four.mys.domain.models.Valute
import team.four.mys.domain.usecases.AddSubscriptionUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

class CreateSubscriptionViewModel(
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase,
    private val retrofit: Retrofit,
    private val addSubscriptionUseCase: AddSubscriptionUseCase
) : ViewModel() {

    private var valute: Valute? = null

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam)
    }

    fun monthYearFromDate(date: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("LLLL")
        return date.format(formatter)
            .replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
    }

    fun daysInMonthArray(date: LocalDate): ArrayList<String> {
        val daysInMonthArray = ArrayList<String>()
        val yearMonth: YearMonth = YearMonth.from(date)
        val daysInMonth: Int = yearMonth.lengthOfMonth()
        val firstOfMonth = date.withDayOfMonth(1)
        val dayOfWeek = firstOfMonth.dayOfWeek.value
        for (i in 1..42) {
            if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                daysInMonthArray.add("")
            } else {
                daysInMonthArray.add((i - dayOfWeek).toString())
            }
        }
        return daysInMonthArray
    }

    suspend fun getCurrencies() {
        valute = retrofit.getCurrencies()
    }

     fun addSubscription(subscription: Subscription) {
        viewModelScope.launch { addSubscriptionUseCase.execute(subscription = subscription) }
    }

}