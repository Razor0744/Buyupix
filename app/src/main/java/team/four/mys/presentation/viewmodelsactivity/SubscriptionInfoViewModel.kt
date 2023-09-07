package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Subscription
import team.four.mys.domain.usecases.DeleteSubscriptionUseCase
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase

class SubscriptionInfoViewModel(
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase,
    private val getSubscriptionInfoUseCase: GetSubscriptionInfoUseCase,
    private val deleteSubscriptionUseCase: DeleteSubscriptionUseCase
) :
    ViewModel() {

    var subscriptionInfo = MutableLiveData<Subscription>()

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam = setStatusBarParam)
    }

    fun getSubscriptionInfo(id: Long) {
        viewModelScope.launch {
            subscriptionInfo.postValue(getSubscriptionInfoUseCase.execute(id = id))
        }
    }

    fun deleteSubscription(subscription: Subscription){
        viewModelScope.launch {
            deleteSubscriptionUseCase.execute(subscription = subscription)
        }
    }
}