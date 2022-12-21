package team.four.mys.data.api.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import team.four.mys.data.api.retrofit.currencies.CurrenciesService

object RetrofitClient {

    //valute
    private val CURRENCIES_URL = "https://www.cbr-xml-daily.ru/"
    val currenciesService: CurrenciesService
        get() = getClient(CURRENCIES_URL).create(CurrenciesService::class.java)


    private var retrofit: Retrofit? = null
    private fun getClient(baseUrl: String): Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}