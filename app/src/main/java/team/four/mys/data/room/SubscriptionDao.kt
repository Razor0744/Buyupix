package team.four.mys.data.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import team.four.mys.domain.models.Subscription

@Dao
interface SubscriptionDao {

    @Query("SELECT * FROM subscriptions")
    suspend fun getSubscriptions(): List<Subscription>

    @Query("SELECT * FROM subscriptions WHERE id == :id")
    suspend fun getSubscriptionInfo(id: Long): Subscription

    @Insert
    suspend fun addSubscription(subscription: Subscription)

    @Update
    suspend fun updateSubscription(subscription: Subscription)

    @Delete
    suspend fun deleteSubscription(subscription: Subscription)
}