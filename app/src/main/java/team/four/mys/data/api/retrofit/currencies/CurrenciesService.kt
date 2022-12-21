package team.four.mys.data.api.retrofit.currencies

import retrofit2.Call
import retrofit2.http.GET
import team.four.mys.domain.models.CurrenciesJSON

interface CurrenciesService {

    @GET("daily_json.js")
    fun getCurrencies(): Call<CurrenciesJSON>

}