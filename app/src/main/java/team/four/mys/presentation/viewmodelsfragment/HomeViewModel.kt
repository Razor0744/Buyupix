package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.models.Valute
import team.four.mys.domain.usecases.GetPriceFireBaseUseCase
import team.four.mys.data.api.retrofit.currencies.Retrofit
import team.four.mys.presentation.other.SetStatusBarColor
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel(
    private val setStatusBarColor: SetStatusBarColor,
    private val retrofit: Retrofit
) : ViewModel() {

    //Price
    private var EUR: Float? = null
    private var BYN: Float? = null
    private var USD: Float? = null
    private var priceUSD: Int? = null
    private var priceBYN: Int? = null
    private var priceEUR: Int? = null
    private var valute: Valute? = null
    var fullPrice = MutableLiveData<Float>()

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    suspend fun fullPrice() {
        priceBYN = GetPriceFireBaseUseCase().execute("BYN")
        priceUSD = GetPriceFireBaseUseCase().execute("USD")
        priceEUR = GetPriceFireBaseUseCase().execute("EUR")
        valute = retrofit.getCurrencies()
        BYN = valute?.BYN?.Value?.toFloat()
        USD = valute?.USD?.Value?.toFloat()
        EUR = valute?.EUR?.Value?.toFloat()
        fullPrice.postValue(priceUSD!! + (priceBYN!! * BYN!! / USD!!) + (priceEUR!! * EUR!! / USD!!))
    }

    fun setStatusBarColor(setStatusBarParam: SetStatusBarParam) {
        setStatusBarColor.execute(setStatusBarParam)
    }
}