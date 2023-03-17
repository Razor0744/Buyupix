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
                    priceStart.toString().toFloat() -
                            deleteSubscriptionParam.price.trim().toFloat()
                val price = hashMapOf(
                    "price" to priceEnd as Number
                )
                db.collection(getUID())
                    .document(deleteSubscriptionParam.priceName)
                    .set(price)
            }
        db.collection(getUID())
            .document("numberOfSubscription")
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("number") != null) {
                    val priceStart = doc.get("number")
                    val priceEnd =
                        priceStart.toString().toInt() - 1
                    val price = hashMapOf(
                        "number" to priceEnd as Number
                    )
                    db.collection(getUID())
                        .document("numberOfSubscription")
                        .set(price)
                }
            }
    }

    override suspend fun getPriceFirebase(string: String): Float = suspendCoroutine {
        db.collection(getUID()).document(string)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("price") != null) {
                    it.resume(doc.get("price").toString().toFloat())
                } else {
                    it.resume(0.toFloat())
                }
            }
            .addOnFailureListener { e ->
                println(e)
                it.resume(0.toFloat())
            }
    }

    override fun setNumberOfSubscriptions() {
        db.collection(getUID())
            .document("numberOfSubscription")
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("number") != null) {
                    val priceStart = doc.get("number")
                    val priceEnd =
                        priceStart.toString().toInt() + 1
                    val price = hashMapOf(
                        "number" to priceEnd as Number
                    )
                    db.collection(getUID())
                        .document("numberOfSubscription")
                        .set(price)
                } else {
                    val price = hashMapOf(
                        "number" to 1 as Number
                    )
                    db.collection(getUID())
                        .document("numberOfSubscription")
                        .set(price)
                }
            }
    }

    override suspend fun getNumberOfSubscriptions(): Number = suspendCoroutine {
        db.collection(getUID())
            .document("numberOfSubscription")
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get("number") != null) {
                    val number = doc.get("number")
                    it.resume(number as Number)
                }
            }
    }

    override fun getUID(): String {
        return user?.uid.toString()
    }

    override fun setCategory(category: String, price: Double) {
        db.collection(getUID())
            .document(category)
            .get()
            .addOnSuccessListener { doc ->
                if (doc.get(category) != null) {
                    val priceStart = doc.get(category)
                    val priceEnd =
                        priceStart.toString().toFloat() + price
                    val data = hashMapOf(
                        category to priceEnd as Number
                    )
                    db.collection(getUID())
                        .document(category)
                        .set(data)
                } else {
                    val data = hashMapOf(
                        category to price as Number
                    )
                    db.collection(getUID())
                        .document(category)
                        .set(data)
                }
            }

    }

    override suspend fun getCategory(category: String): DocumentSnapshot = suspendCoroutine {
        db.collection(getUID())
            .document(category)
            .get()
            .addOnSuccessListener { document ->
                it.resume(document)
            }
    }
}