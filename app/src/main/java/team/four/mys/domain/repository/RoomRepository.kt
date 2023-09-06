package team.four.mys.domain.repository

import team.four.mys.domain.models.Subscription

interface RoomRepository {

    suspend fun getSubscriptions(): List<Subscription>

    suspend fun addSubscription(subscription: Subscription)

    suspend fun updateSubscription(subscription: Subscription)

    suspend fun deleteSubscription(subscription: Subscription)
}