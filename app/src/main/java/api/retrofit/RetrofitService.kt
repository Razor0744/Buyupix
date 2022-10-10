package api.retrofit

import models.Rates
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {

    @GET("cbr.json")
    fun getRates(): Call<Rates>
}