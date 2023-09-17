package team.four.mys.domain.repository

import team.four.mys.data.room.Subscription

interface RoomRepository {

    suspend fun getSubscriptions(): List<Subscription>

    suspend fun getSubscriptionInfo(id: Long): Subscription

    suspend fun addSubscription(subscription: Subscription)

    suspend fun updateSubscription(subscription: Subscription)

    suspend fun deleteSubscription(subscription: Subscription)
}