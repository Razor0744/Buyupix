package team.four.mys.domain.repository

import team.four.mys.domain.models.CurrenciesJSON
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("cbr.json")
    fun getRates(): Call<CurrenciesJSON>
}