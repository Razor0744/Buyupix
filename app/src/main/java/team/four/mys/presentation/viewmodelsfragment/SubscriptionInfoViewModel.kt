package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import team.four.mys.data.room.Subscription
import team.four.mys.domain.usecases.DeleteSubscriptionUseCase
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase

class SubscriptionInfoViewModel(
    private val getSubscriptionInfoUseCase: GetSubscriptionInfoUseCase,
    private val deleteSubscriptionUseCase: DeleteSubscriptionUseCase
) : ViewModel() {

    fun getSubscriptionInfo(id: Long) = flow {
        emit(getSubscriptionInfoUseCase.execute(id = id))
    }

    fun deleteSubscription(subscription: Subscription) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSubscriptionUseCase.execute(subscription = subscription)
        }
    }
}