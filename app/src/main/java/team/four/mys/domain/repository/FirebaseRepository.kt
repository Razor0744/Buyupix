package team.four.mys.domain.repository

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.models.SubscriptionInfoParam

interface FirebaseRepository {

    suspend fun getSubscriptionInfo(subscriptionInfoParam: SubscriptionInfoParam): DocumentSnapshot

    fun deleteSubscription(deleteSubscriptionParam: DeleteSubscriptionParam)

    suspend fun getPriceFirebase(string: String): Float

    fun getUID(): String

    fun setNumberOfSubscriptions()

    suspend fun getNumberOfSubscriptions(): Number

    suspend fun getCategory(): DocumentSnapshot

    fun setCategory(category: String, price: Double)
}