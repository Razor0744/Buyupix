package team.four.mys.domain.repository

import team.four.mys.data.room.Subscription

interface RoomRepository {

    fun getSubscriptions(): List<Subscription>

    fun getSubscriptionInfo(id: Long): Subscription

    fun addSubscription(subscription: Subscription)

    fun updateSubscription(subscription: Subscription)

    fun deleteSubscription(subscription: Subscription)
}