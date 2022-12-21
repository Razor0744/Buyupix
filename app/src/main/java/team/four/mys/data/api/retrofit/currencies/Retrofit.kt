package team.four.mys.data.api.retrofit.currencies

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.four.mys.data.api.retrofit.RetrofitClient
import team.four.mys.domain.models.CurrenciesJSON
import team.four.mys.domain.models.Valute
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Retrofit(private val retrofitClient: RetrofitClient) {

    suspend fun getCurrencies(): Valute? = suspendCoroutine {
        retrofitClient.currenciesService.getCurrencies().enqueue(object : Callback<CurrenciesJSON> {
            override fun onResponse(
                call: Call<CurrenciesJSON>,
                response: Response<CurrenciesJSON>
            ) {
                val responses = response.body() as CurrenciesJSON
                it.resume(responses.Valute)

            }

            override fun onFailure(call: Call<CurrenciesJSON>, t: Throwable) {
                println(t)
            }
        })
    }
}