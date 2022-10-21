package team.four.mys.domain.usecases

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class GetFirebasePriceUseCase {

    private var db = Firebase.firestore

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
}