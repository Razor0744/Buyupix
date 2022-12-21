package team.four.mys.data.repository

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.data.database.FirebaseDatabase
import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.models.SubscriptionInfoParam
import team.four.mys.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl(private val firebaseDatabase: FirebaseDatabase) : FirebaseRepository {

    override suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam): DocumentSnapshot {
        return firebaseDatabase.getSubscriptionInfo(subscriptionInfoParam = subscriptionInfoParam)
    }

    override fun deleteSubscription(deleteSubscriptionParam: DeleteSubscriptionParam) {
        firebaseDatabase.deleteSubscription(deleteSubscriptionParam = deleteSubscriptionParam)
    }

    override suspend fun getPriceFirebase(string: String): Int {
        return firebaseDatabase.getPriceFirebase(string = string)
    }

    override fun getUID(): String {
        return firebaseDatabase.getUID()
    }

}