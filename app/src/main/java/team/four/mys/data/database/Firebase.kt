package team.four.mys.data.database

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.models.SubscriptionInfoParam
import team.four.mys.domain.usecases.GetUIDUseCase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Firebase : FirebaseDatabase {

    private val db = Firebase.firestore

    override suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam): DocumentSnapshot =
        suspendCoroutine {
            db.collection(subscriptionInfoParam.uid).document(subscriptionInfoParam.date)
                .collection(subscriptionInfoParam.dateType)
                .document(subscriptionInfoParam.name)
                .get()
                .addOnSuccessListener { document ->
                    it.resume(document)
                }
        }

    override fun deleteSubscription(deleteSubscriptionParam: DeleteSubscriptionParam) {
        db.collection(deleteSubscriptionParam.uid)
            .document(deleteSubscriptionParam.date)
            .collection(deleteSubscriptionParam.dateType)
            .document(deleteSubscriptionParam.name)
            .delete()
    }

    override suspend fun getPriceFirebase(string: String): Int = suspendCoroutine {
        db.collection(GetUIDUseCase().execute()).document(string)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    it.resume(Integer.parseInt(doc.get("price").toString()))
                } else {
                    it.resume(0)
                }
            }
            .addOnFailureListener { e ->
                println(e)
                it.resume(0)
            }
    }
}