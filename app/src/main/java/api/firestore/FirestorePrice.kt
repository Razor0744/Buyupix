package api.firestore

import api.retrofit.Currencies
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import models.Rates
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FirestorePrice() {

    private val db = Firebase.firestore

    fun fullPrice(){
        val priceUSD = getPriceUSD()
        val priceBYN = getPriceBYN()
        val priceEUR = getPriceEUR()
    }

    private fun retrofit() {
        Currencies.retrofitService.getRates().enqueue(object : Callback<Rates> {
            override fun onResponse(call: Call<Rates>, response: Response<Rates>) {
                val responses = response.body() as Rates
                println(responses.rates?.EUR)
            }

            override fun onFailure(call: Call<Rates>, t: Throwable) {
                println(t)
            }
        })
        println()
    }

    private fun getPriceUSD(): Int {
        var price = 0
        db.collection(uid()).document("USD")
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    price = doc.get("price") as Int
                }
            }
        return price
    }

    private fun getPriceBYN(): Int {
        var price = 0
        db.collection(uid()).document("BYN")
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    price = doc.get("price") as Int
                }
            }
        return price
    }

    private fun getPriceEUR(): Int {
        var price = 0
        db.collection(uid()).document("EUR")
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    price = doc.get("price") as Int
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