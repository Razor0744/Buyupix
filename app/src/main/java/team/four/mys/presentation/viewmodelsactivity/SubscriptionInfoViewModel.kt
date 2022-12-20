package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.models.SubscriptionInfoParam
import team.four.mys.domain.usecases.DeleteSubscriptionUseCase
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase

class SubscriptionInfoViewModel(
    private val deleteSubscriptionUseCase: DeleteSubscriptionUseCase,
    private val getSubscriptionInfoUseCase: GetSubscriptionInfoUseCase
) :
    ViewModel() {

    val documentLiveData = MutableLiveData<DocumentSnapshot>()

    fun deleteSubscription(subscriptionInfoParam: SubscriptionInfoParam) {
        deleteSubscriptionUseCase.execute(subscriptionInfoParam)
    }

    suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam) {
        val getValue = getSubscriptionInfoUseCase.execute(subscriptionInfoParam = subscriptionInfoParam)
        documentLiveData.postValue(getValue)
    }
}