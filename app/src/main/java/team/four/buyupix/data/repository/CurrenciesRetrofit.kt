package team.four.buyupix.data.repository

import team.four.buyupix.data.api.retrofit.RetrofitClient
import team.four.buyupix.domain.repository.RetrofitService

object CurrenciesRetrofit {

    private val BASE_URL = "https://www.cbr-xml-daily.ru/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}