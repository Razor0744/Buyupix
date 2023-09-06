package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.SubscriptionInfoParam
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase

class SubscriptionInfoViewModel(
    private val getSubscriptionInfoUseCase: GetSubscriptionInfoUseCase,
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase,
) :
    ViewModel() {

    val documentLiveData = MutableLiveData<DocumentSnapshot>()

    suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam) {
        val getValue =
            getSubscriptionInfoUseCase.execute(subscriptionInfoParam = subscriptionInfoParam)
        documentLiveData.postValue(getValue)
    }

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam = setStatusBarParam)
    }
}