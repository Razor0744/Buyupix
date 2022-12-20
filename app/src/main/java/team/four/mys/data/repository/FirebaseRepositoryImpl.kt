package team.four.mys.data.repository

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.data.database.FirebaseDatabase
import team.four.mys.domain.models.SubscriptionInfoParam
import team.four.mys.domain.repository.FirebaseRepository

class FirebaseRepositoryImpl(private val firebaseDatabase: FirebaseDatabase) : FirebaseRepository {

    override suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam): DocumentSnapshot {
        return firebaseDatabase.getSubscriptionInfo(subscriptionInfoParam = subscriptionInfoParam)
    }

}