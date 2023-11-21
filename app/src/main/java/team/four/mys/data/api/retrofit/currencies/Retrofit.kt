package team.four.mys.data.api.retrofit.currencies

import io.reactivex.rxjava3.core.Single
import team.four.mys.data.api.retrofit.RetrofitClient
import team.four.mys.domain.models.CurrenciesJSON

class Retrofit(private val retrofitClient: RetrofitClient) {

    fun getCurrencies(): Single<CurrenciesJSON> {
        return retrofitClient.currenciesService.getCurrencies()
    }
}