package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeViewModel(
    private val getUIDUseCase: GetUIDUseCase,
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {

//    private var subscriptionsLiveData = MutableLiveData<List<Subscription>>()
//    val subscriptions: LiveData<List<Subscription>> = subscriptionsLiveData

    fun getSubscriptions() = flow {
        emit(getSubscriptionsUseCase.execute())
    }

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    fun getUID(): String {
        return getUIDUseCase.execute()
    }
}