package team.four.mys.domain.repository

import retrofit2.Call
import retrofit2.http.GET
import team.four.mys.domain.models.CurrenciesJSON

interface RetrofitService {

    @GET("daily_json.js")
    fun getRates(): Call<CurrenciesJSON>

}