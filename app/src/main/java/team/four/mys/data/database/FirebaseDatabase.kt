package team.four.mys.data.database

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.models.SubscriptionInfoParam

interface FirebaseDatabase {

    suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam): DocumentSnapshot

    fun deleteSubscription(deleteSubscriptionParam: DeleteSubscriptionParam)

    suspend fun getPriceFirebase(string: String): Float

    fun setNumberOfSubscriptions()

    suspend fun getNumberOfSubscriptions(): Number

    fun getUID(): String
}