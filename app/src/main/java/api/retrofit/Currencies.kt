package api.retrofit

object Currencies {

    private val BASE_URL = "https://cdn.cur.su/api/"
    val retrofitService: RetrofitService
        get() =RetrofitClient.getClient(BASE_URL).create(RetrofitService::class.java)
}