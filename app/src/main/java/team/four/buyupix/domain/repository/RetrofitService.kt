package team.four.buyupix.domain.repository

import retrofit2.Call
import retrofit2.http.GET
import team.four.buyupix.domain.models.CurrenciesJSON

interface RetrofitService {

    @GET("daily_json.js")
    fun getRates(): Call<CurrenciesJSON>

}