package team.four.buyupix.presentation.viewmodelsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import team.four.buyupix.domain.usecases.GetPriceFireBaseUseCase
import team.four.buyupix.domain.usecases.RetrofitCurrenciesUseCase
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {

    //Price
    private var EUR: Float? = null
    private var BYN: Float? = null
    private var USD: Float? = null
    private var priceUSD: Int? = null
    private var priceBYN: Int? = null
    private var priceEUR: Int? = null
    var fullPrice = MutableLiveData<Float>()

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    suspend fun fullPrice() {
        priceBYN = GetPriceFireBaseUseCase().execute("BYN")
        priceUSD = GetPriceFireBaseUseCase().execute("USD")
        priceEUR = GetPriceFireBaseUseCase().execute("EUR")
        USD = RetrofitCurrenciesUseCase().execute(valute = "USD")?.toFloat()
        EUR = RetrofitCurrenciesUseCase().execute(valute = "EUR")?.toFloat()
        BYN = RetrofitCurrenciesUseCase().execute(valute = "BYN")?.toFloat()
        fullPrice.postValue(priceUSD!! + (priceBYN!! * BYN!! / USD!!) + (priceEUR!! * EUR!! / USD!!))
    }

//    private suspend fun retrofit() {
//        RetrofitCurrenciesUseCase().execute()
//    }
}