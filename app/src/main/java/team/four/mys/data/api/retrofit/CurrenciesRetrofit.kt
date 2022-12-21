package team.four.mys.data.api.retrofit

import team.four.mys.domain.repository.RetrofitService

object CurrenciesRetrofit {

    private val BASE_URL = "https://www.cbr-xml-daily.ru/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}