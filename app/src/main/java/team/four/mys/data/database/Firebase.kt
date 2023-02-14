package team.four.mys.data.database

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.models.SubscriptionInfoParam
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

class Firebase : FirebaseDatabase {

    private val db = Firebase.firestore
    private val user = FirebaseAuth.getInstance().currentUser

    override suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam): DocumentSnapshot =
        suspendCoroutine {
            db.collection(getUID()).document(subscriptionInfoParam.date)
                .collection(subscriptionInfoParam.dateType)
                .document(subscriptionInfoParam.name)
                .get()
                .addOnSuccessListener { document ->
                    it.resume(document)
                }
        }

    override fun deleteSubscription(deleteSubscriptionParam: DeleteSubscriptionParam) {
        db.collection(getUID())
            .document(deleteSubscriptionParam.date)
            .collection(deleteSubscriptionParam.dateType)
            .document(deleteSubscriptionParam.name)
            .delete()
        db.collection(getUID())
            .document(deleteSubscriptionParam.priceName)
            .get()
            .addOnSuccessListener { doc ->
                val priceStart = doc.get("price")
                val priceEnd =
                    Integer.parseInt(priceStart.toString()) - Integer.parseInt(
                        deleteSubscriptionParam.price
                    )
                val price = hashMapOf(
                    "price" to priceEnd as Number
                )
                db.collection(getUID())
                    .document(deleteSubscriptionParam.priceName)
                    .set(price)
            }
    }

    override suspend fun getPriceFirebase(string: String): Int = suspendCoroutine {
        db.collection(getUID()).document(string)
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

    override fun getUID(): String {
        return user?.uid.toString()
    }
}