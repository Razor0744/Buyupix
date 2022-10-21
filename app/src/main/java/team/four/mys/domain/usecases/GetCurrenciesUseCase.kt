package team.four.mys.domain.usecases

import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import team.four.mys.data.repository.CurrenciesRetrofit
import team.four.mys.domain.models.Currencies
import team.four.mys.domain.models.CurrenciesJSON

class GetCurrenciesUseCase {

    private var EUR: Float? = null
    private var BYN: Float? = null

    suspend fun execute(): Currencies = coroutineScope {
        val currencies = async {
            CurrenciesRetrofit.retrofitService.getRates()
                .enqueue(object : Callback<CurrenciesJSON> {
                    override fun onResponse(
                        call: Call<CurrenciesJSON>,
                        response: Response<CurrenciesJSON>
                    ) {
                        val responses = response.body() as CurrenciesJSON
                        EUR = responses.rates?.EUR
                        BYN = responses.rates?.BYN
                        println("$EUR $BYN")
                    }

                    override fun onFailure(call: Call<CurrenciesJSON>, t: Throwable) {
                        println(t)
                    }
                })
            println("good")
        }
        currencies.await()
        println("not good")
        return@coroutineScope Currencies(EUR = EUR, BYN = BYN)
    }
}