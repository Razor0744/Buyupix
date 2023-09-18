package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.mys.data.api.retrofit.currencies.Retrofit
import team.four.mys.data.room.Subscription
import team.four.mys.domain.models.Valute
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class HomeViewModel(
    private val retrofit: Retrofit,
    private val getUIDUseCase: GetUIDUseCase,
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {

    //Price
    private var EUR: Float? = null
    private var BYN: Float? = null
    private var USD: Float? = null
    private var valute: Valute? = null
    var fullPrice = MutableLiveData<Float>()

    var subscriptions = MutableLiveData<List<Subscription>>()

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    fun fullPrice() {
        viewModelScope.launch {
            valute = retrofit.getCurrencies()
            BYN = valute?.BYN?.Value?.toFloat()
            USD = valute?.USD?.Value?.toFloat()
            EUR = valute?.EUR?.Value?.toFloat()
        }
    }

    fun getUID(): String {
        return getUIDUseCase.execute()
    }

    fun getSubscriptions() {
        viewModelScope.launch(Dispatchers.Default) {
            subscriptions.postValue(getSubscriptionsUseCase.execute())
        }
    }

    override fun onCleared() {
        super.onCleared()
        println("home vm cleared")
    }

    init {
        println("home vm created")
    }
}