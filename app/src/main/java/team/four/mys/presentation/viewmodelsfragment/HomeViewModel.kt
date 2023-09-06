package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Valute
import team.four.mys.domain.usecases.GetPriceFireBaseUseCase
import team.four.mys.data.api.retrofit.currencies.Retrofit
import team.four.mys.domain.models.Subscription
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(
    private val setStatusBarColorUseCase: SetStatusBarColorUseCase,
    private val retrofit: Retrofit,
    private val getPriceFireBaseUseCase: GetPriceFireBaseUseCase,
    private val getUIDUseCase: GetUIDUseCase,
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {

    //Price
    private var EUR: Float? = null
    private var BYN: Float? = null
    private var USD: Float? = null
    private var priceUSD: Float? = null
    private var priceBYN: Float? = null
    private var priceEUR: Float? = null
    private var valute: Valute? = null
    var fullPrice = MutableLiveData<Float>()

    var subscriptions = MutableLiveData<List<Subscription>>()

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    suspend fun fullPrice() {
        priceBYN = getPriceFireBaseUseCase.execute(string = "BYN")
        priceUSD = getPriceFireBaseUseCase.execute(string = "USD")
        priceEUR = getPriceFireBaseUseCase.execute(string = "EUR")
        valute = retrofit.getCurrencies()
        BYN = valute?.BYN?.Value?.toFloat()
        USD = valute?.USD?.Value?.toFloat()
        EUR = valute?.EUR?.Value?.toFloat()
        fullPrice.postValue(priceUSD!! + (priceBYN!! * BYN!! / USD!!) + (priceEUR!! * EUR!! / USD!!))
    }

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColorUseCase.execute(setStatusBarParam)
    }

    fun getUID(): String {
        return getUIDUseCase.execute()
    }

    fun getSubscriptions() {
        viewModelScope.launch {
            subscriptions.postValue(getSubscriptionsUseCase.execute())
        }
    }
}