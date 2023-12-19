package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.mys.data.room.Subscription
import team.four.mys.domain.usecases.AddSubscriptionUseCase
import team.four.mys.domain.usecases.GetCategoryOfSubscriptionUseCase
import team.four.mys.domain.usecases.GetCurrencyIconUseCase
import team.four.mys.domain.usecases.GetUrlImageUseCase
import javax.inject.Inject

class CreateSubscriptionViewModel @Inject constructor(
    private val addSubscriptionUseCase: AddSubscriptionUseCase,
    private val getCategoryOfSubscriptionUseCase: GetCategoryOfSubscriptionUseCase,
    private val getUrlImageUseCase: GetUrlImageUseCase,
    private val getCurrencyIconUseCase: GetCurrencyIconUseCase
) : ViewModel() {

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