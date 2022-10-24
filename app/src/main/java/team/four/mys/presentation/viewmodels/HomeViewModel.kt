package team.four.mys.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.four.mys.data.repository.CurrenciesRetrofit
import team.four.mys.domain.models.CurrenciesJSON
import team.four.mys.domain.usecases.GetUIDUseCase
import java.text.SimpleDateFormat
import java.util.*

class HomeViewModel : ViewModel() {

    private var db = Firebase.firestore

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

    fun fullPrice() {
        getPrice("USD")
        getPrice("BYN")
        getPrice("EUR")
        retrofit()
        while (true) {
            if (EUR != null && BYN != null && priceBYN != null && priceUSD != null && priceEUR != null) {
                fullPrice.postValue(priceUSD!! + (priceBYN!! / BYN!!) + (priceEUR!! / EUR!!))
                break
            }
        }
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
            }

            override fun onFailure(call: Call<CurrenciesJSON>, t: Throwable) {
                println(t)
            }
        })
    }

    private fun getPrice(string: String) {
        db.collection(GetUIDUseCase().execute()).document(string)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    when (string) {
                        "USD" -> priceUSD = Integer.parseInt(doc.get("price").toString())
                        "BYN" -> priceBYN = Integer.parseInt(doc.get("price").toString())
                        "EUR" -> priceEUR = Integer.parseInt(doc.get("price").toString())
                    }
                } else {
                    when (string) {
                        "USD" -> priceUSD = 0
                        "BYN" -> priceBYN = 0
                        "EUR" -> priceEUR = 0
                    }
                }
            }
    }
}