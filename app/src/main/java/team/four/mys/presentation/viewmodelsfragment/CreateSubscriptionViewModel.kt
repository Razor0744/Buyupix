package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.mys.data.api.retrofit.currencies.Retrofit
import team.four.mys.data.room.Subscription
import team.four.mys.domain.models.Valute
import team.four.mys.domain.usecases.AddSubscriptionUseCase
import team.four.mys.domain.usecases.GetCategoryOfSubscriptionUseCase
import team.four.mys.domain.usecases.GetCurrencyIconUseCase
import team.four.mys.domain.usecases.GetUrlImageUseCase
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.Locale

class CreateSubscriptionViewModel(
    private val retrofit: Retrofit,
    private val addSubscriptionUseCase: AddSubscriptionUseCase,
    private val getCategoryOfSubscriptionUseCase: GetCategoryOfSubscriptionUseCase,
    private val getUrlImageUseCase: GetUrlImageUseCase,
    private val getCurrencyIconUseCase: GetCurrencyIconUseCase
) : ViewModel() {

    private var valute: Valute? = null

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
        viewModelScope.launch(Dispatchers.IO) { addSubscriptionUseCase.execute(subscription = subscription) }
    }

    fun getCategoryOfSubscription(name: String): String {
        return getCategoryOfSubscriptionUseCase.execute(name = name)
    }

    fun getUrlImage(name: String): String {
        return getUrlImageUseCase.execute(name = name)
    }

    fun getCurrencyIcon(currency: String): String {
        return getCurrencyIconUseCase.execute(currency = currency)
    }
}