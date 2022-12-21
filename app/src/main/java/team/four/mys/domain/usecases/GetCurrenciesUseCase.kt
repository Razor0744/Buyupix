package team.four.mys.domain.usecases

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.four.mys.data.api.retrofit.CurrenciesRetrofit
import team.four.mys.domain.models.CurrenciesJSON
import team.four.mys.domain.models.Valute
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetCurrenciesUseCase {

    suspend fun execute(): Valute? = suspendCoroutine {
        CurrenciesRetrofit.retrofitService.getRates().enqueue(object : Callback<CurrenciesJSON> {
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