package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.CategoryOfSubscriptionUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import team.four.mys.domain.usecases.SetCategoryUseCase
import team.four.mys.domain.usecases.SetNumberOfSubscriptionsUseCase
import team.four.mys.presentation.other.SetStatusBarColor
import java.time.LocalDate
import java.time.YearMonth
import java.time.format.DateTimeFormatter
import java.util.*

class CreateSubscriptionViewModel(
    private val setStatusBarColor: SetStatusBarColor,
    private val getUIDUseCase: GetUIDUseCase,
    private val setNumberOfSubscriptionsUseCase: SetNumberOfSubscriptionsUseCase,
    private val setCategoryUseCase: SetCategoryUseCase,
    private val categoryOfSubscriptionUseCase: CategoryOfSubscriptionUseCase
) : ViewModel() {

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColor.execute(setStatusBarParam)
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

    fun setNumberOfSubscriptions() {
        setNumberOfSubscriptionsUseCase.execute()
    }

    fun getUID(): String {
        return getUIDUseCase.execute()
    }

    fun setCategory(category: String, price: Double) {
        setCategoryUseCase.execute(category = category, price = price)
    }

    fun categoryOfSubscriptions(name: String): String {
        return categoryOfSubscriptionUseCase.execute(name)
    }

}