package team.four.buyupix.domain.usecases

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.four.buyupix.data.repository.CurrenciesRetrofit
import team.four.buyupix.domain.models.CurrenciesJSON
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class RetrofitCurrenciesUseCase {

    suspend fun execute(valute: String): Double? = suspendCoroutine {
        CurrenciesRetrofit.retrofitService.getRates().enqueue(object : Callback<CurrenciesJSON> {
            override fun onResponse(
                call: Call<CurrenciesJSON>,
                response: Response<CurrenciesJSON>
            ) {
                val responses = response.body() as CurrenciesJSON
                when (valute) {
                    "BYN" -> it.resume(responses.Valute?.BYN?.Value)
                    "USD" -> it.resume(responses.Valute?.USD?.Value)
                    "EUR" -> it.resume(responses.Valute?.EUR?.Value)
                }
            }

            override fun onFailure(call: Call<CurrenciesJSON>, t: Throwable) {
                println(t)
            }
        })
    }
}