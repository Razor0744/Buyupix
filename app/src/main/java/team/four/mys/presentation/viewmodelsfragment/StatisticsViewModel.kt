package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.four.mys.data.repository.CurrenciesRetrofit
import team.four.mys.domain.models.CurrenciesJSON
import team.four.mys.domain.usecases.GetPriceFireBaseUseCase
import java.text.SimpleDateFormat
import java.util.*

class StatisticsViewModel : ViewModel() {

    //Price
    private var EUR: Float? = null
    private var BYN: Float? = null
    private var priceUSD: Int? = null
    private var priceBYN: Int? = null
    private var priceEUR: Int? = null
    var fullPrice = MutableLiveData<Float>()

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    suspend fun fullPrice() {
        priceBYN = GetPriceFireBaseUseCase().getPriceFireBase("BYN")
        priceUSD = GetPriceFireBaseUseCase().getPriceFireBase("USD")
        priceEUR = GetPriceFireBaseUseCase().getPriceFireBase("EUR")
        retrofit()
    }

    private fun retrofit() {
        CurrenciesRetrofit.retrofitService.getRates().enqueue(object : Callback<CurrenciesJSON> {
            override fun onResponse(
                call: Call<CurrenciesJSON>,
                response: Response<CurrenciesJSON>
            ) {
                val responses = response.body() as CurrenciesJSON
                EUR = responses.rates?.EUR
                BYN = responses.rates?.BYN
                fullPrice.postValue(priceUSD!! + (priceBYN!! / BYN!!) + (priceEUR!! / EUR!!))
            }

            override fun onFailure(call: Call<CurrenciesJSON>, t: Throwable) {
                println(t)
            }
        })
    }
}