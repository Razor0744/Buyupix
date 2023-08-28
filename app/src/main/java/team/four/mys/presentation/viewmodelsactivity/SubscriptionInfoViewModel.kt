package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SubscriptionInfoParam
import team.four.mys.domain.usecases.DeleteSubscriptionUseCase
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase

class SubscriptionInfoViewModel(
    private val deleteSubscriptionUseCase: DeleteSubscriptionUseCase,
    private val getSubscriptionInfoUseCase: GetSubscriptionInfoUseCase,
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase,
) :
    ViewModel() {

    val documentLiveData = MutableLiveData<DocumentSnapshot>()

    fun deleteSubscription(deleteSubscriptionParam: DeleteSubscriptionParam) {
        deleteSubscriptionUseCase.execute(deleteSubscriptionParam = deleteSubscriptionParam)
    }

    suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam) {
        val getValue =
            getSubscriptionInfoUseCase.execute(subscriptionInfoParam = subscriptionInfoParam)
        documentLiveData.postValue(getValue)
    }

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam = setStatusBarParam)
    }
}