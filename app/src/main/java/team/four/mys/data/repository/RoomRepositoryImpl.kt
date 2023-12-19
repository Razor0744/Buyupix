package team.four.mys.data.repository

import team.four.mys.data.room.SubscriptionDao
import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository
import javax.inject.Inject

class RoomRepositoryImpl @Inject constructor(private val subscriptionDao: SubscriptionDao) : RoomRepository {

    override fun getSubscriptions(): List<Subscription> {
        return subscriptionDao.getSubscriptions()
    }

    override fun getSubscriptionInfo(id: Long): Subscription {
        return subscriptionDao.getSubscriptionInfo(id = id)
    }

    override fun addSubscription(subscription: Subscription) {
        subscriptionDao.addSubscription(subscription = subscription)
    }

    override fun updateSubscription(subscription: Subscription) {
        subscriptionDao.updateSubscription(subscription = subscription)
    }

    override fun deleteSubscription(subscription: Subscription) {
        subscriptionDao.deleteSubscription(subscription = subscription)
    }
}