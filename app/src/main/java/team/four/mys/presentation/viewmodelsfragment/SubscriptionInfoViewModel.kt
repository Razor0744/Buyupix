package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.mys.domain.models.Subscription
import team.four.mys.domain.usecases.DeleteSubscriptionUseCase
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase

class SubscriptionInfoViewModel(
    private val getSubscriptionInfoUseCase: GetSubscriptionInfoUseCase,
    private val deleteSubscriptionUseCase: DeleteSubscriptionUseCase
) :
    ViewModel() {

    var subscriptionInfo = MutableLiveData<Subscription>()

    fun getSubscriptionInfo(id: Long) {
        viewModelScope.launch(Dispatchers.IO) {
            subscriptionInfo.postValue(getSubscriptionInfoUseCase.execute(id = id))
        }
    }

    fun deleteSubscription(subscription: Subscription) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteSubscriptionUseCase.execute(subscription = subscription)
        }
    }
}