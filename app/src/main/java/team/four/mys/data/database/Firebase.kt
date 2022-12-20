package team.four.mys.data.database

import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import team.four.mys.domain.models.SubscriptionInfoParam
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
}