package team.four.buyupix.domain.repository

import team.four.buyupix.domain.models.CurrenciesJSON
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("cbr.json")
    fun getRates(): Call<CurrenciesJSON>
}