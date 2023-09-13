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
    fun getSubscriptions(): List<Subscription>

    @Query("SELECT * FROM subscriptions WHERE id == :id")
    fun getSubscriptionInfo(id: Long): Subscription

    @Insert
    fun addSubscription(subscription: Subscription)

    @Update
    fun updateSubscription(subscription: Subscription)

    @Delete
    fun deleteSubscription(subscription: Subscription)
}