package team.four.buyupix.domain.usecases

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class GetPriceFireBaseUseCase {

    private val db = Firebase.firestore

    suspend fun getPriceFireBase(string: String): Int = suspendCoroutine {
        db.collection(GetUIDUseCase().getUID()).document(string)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    it.resume(Integer.parseInt(doc.get("price").toString()))
                }
            }
    }
}
