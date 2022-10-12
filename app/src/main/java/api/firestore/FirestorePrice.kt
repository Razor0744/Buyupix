package api.firestore

import api.retrofit.Currencies
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import models.Rates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirestorePrice {

    private val db = Firebase.firestore

    private var EUR: Float? = null
    private var BYN: Float? = null
    private var priceUSD: Int? = null
    private var priceBYN: Int? = null
    private var priceEUR: Int? = null

    fun fullPrice() {
        getPrice("USD")
        getPrice("BYN")
        getPrice("EUR")
        retrofit()
        while (true) {
            if (EUR != null && BYN != null && priceBYN != null && priceUSD != null && priceEUR != null) {
                val fullPrice = priceUSD!! + (priceBYN!! * BYN!!) + (priceEUR!! * EUR!!)
                println(fullPrice)
                break
            }
        }
    }

    private fun retrofit() {
        Currencies.retrofitService.getRates().enqueue(object : Callback<Rates> {
            override fun onResponse(call: Call<Rates>, response: Response<Rates>) {
                val responses = response.body() as Rates
                EUR = responses.rates?.EUR
                BYN = responses.rates?.BYN
            }

            override fun onFailure(call: Call<Rates>, t: Throwable) {
                println(t)
            }
        })
    }

    private fun getPrice(string: String) {
        db.collection(uid()).document(string)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    when (string) {
                        "USD" -> priceUSD = Integer.parseInt(doc.get("price").toString())
                        "BYN" -> priceBYN = Integer.parseInt(doc.get("price").toString())
                        "EUR" -> priceEUR = Integer.parseInt(doc.get("price").toString())
                    }
                } else {
                    when (string) {
                        "USD" -> priceUSD = 0
                        "BYN" -> priceBYN = 0
                        "EUR" -> priceEUR = 0
                    }
                }
            }
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }
}