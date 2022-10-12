package api.firestore

import api.retrofit.Currencies
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import models.Rates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirestorePrice {

    private val db = Firebase.firestore

    private var EUR: Float? = null
    private var BYN: Float? = null

    private fun fullPrice() {
        val priceUSD = getPrice("USD")
        val priceBYN = getPrice("BYN")
        val priceEUR = getPrice("EUR")
        val fullPrice = priceUSD + (priceBYN * BYN!!) + (priceEUR * EUR!!)
        println(fullPrice)
    }

    fun retrofit() {
        Currencies.retrofitService.getRates().enqueue(object : Callback<Rates> {
            override fun onResponse(call: Call<Rates>, response: Response<Rates>) {
                val responses = response.body() as Rates
                EUR = responses.rates?.EUR
                BYN = responses.rates?.BYN
                fullPrice()
            }

            override fun onFailure(call: Call<Rates>, t: Throwable) {
                println(t)
            }
        })
    }

    private fun getPrice(string: String): Int {
        var price = 0
        db.collection(uid()).document(string)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    price = Integer.parseInt(doc.get("price").toString())
                }
            }
        return price
    }

    private fun uid(): String {
        // get UID
        val user = FirebaseAuth.getInstance().currentUser
        val uid = user?.uid
        return uid.toString()
    }
}