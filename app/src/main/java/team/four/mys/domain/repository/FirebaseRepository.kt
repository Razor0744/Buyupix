package team.four.mys.domain.repository

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.models.SubscriptionInfoParam

interface FirebaseRepository {

    suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam): DocumentSnapshot

    fun deleteSubscription(deleteSubscriptionParam: DeleteSubscriptionParam)

    suspend fun getPriceFirebase(string: String): Int

    fun getUID(): String
}