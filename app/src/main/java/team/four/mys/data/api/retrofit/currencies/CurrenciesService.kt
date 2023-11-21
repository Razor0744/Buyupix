package team.four.mys.data.api.retrofit.currencies

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import team.four.mys.domain.models.CurrenciesJSON

interface CurrenciesService {

    @GET("daily_json.js")
    fun getCurrencies(): Single<CurrenciesJSON>

}