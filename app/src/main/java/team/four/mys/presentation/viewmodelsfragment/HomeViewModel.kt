package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.mys.data.room.Subscription
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeViewModel(
    private val getUIDUseCase: GetUIDUseCase,
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {

    private var subscriptionsData = MutableLiveData<List<Subscription>>()
    val subscriptions: LiveData<List<Subscription>> = subscriptionsData

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    fun getUID(): String {
        return getUIDUseCase.execute()
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            subscriptionsData.postValue(getSubscriptionsUseCase.execute())
        }
    }
}