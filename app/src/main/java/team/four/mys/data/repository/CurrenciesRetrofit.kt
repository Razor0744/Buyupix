package team.four.mys.data.repository

import team.four.mys.data.api.retrofit.RetrofitClient
import team.four.mys.domain.repository.RetrofitService

object CurrenciesRetrofit {

    private val BASE_URL = "https://cdn.cur.su/api/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}