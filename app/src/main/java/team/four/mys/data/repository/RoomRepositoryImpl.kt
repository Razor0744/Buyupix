package team.four.mys.data.repository

import team.four.mys.data.room.SubscriptionDao
import team.four.mys.domain.models.Subscription
import team.four.mys.domain.repository.RoomRepository

class RoomRepositoryImpl(private val subscriptionDao: SubscriptionDao) : RoomRepository {

    override suspend fun getSubscriptions(): List<Subscription> {
        return subscriptionDao.getSubscriptions()
    }

    override suspend fun getSubscriptionInfo(id: Long): Subscription {
       return subscriptionDao.getSubscriptionInfo(id = id)
    }

    override suspend fun addSubscription(subscription: Subscription) {
        subscriptionDao.addSubscription(subscription = subscription)
    }

    override suspend fun updateSubscription(subscription: Subscription) {
        subscriptionDao.updateSubscription(subscription = subscription)
    }

    override suspend fun deleteSubscription(subscription: Subscription) {
        subscriptionDao.deleteSubscription(subscription = subscription)
    }
}