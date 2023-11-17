package team.four.mys.data.repository

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import team.four.mys.data.room.SubscriptionDao
import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository

class RoomRepositoryImpl(private val subscriptionDao: SubscriptionDao) : RoomRepository {

    override fun getSubscriptions(): Single<List<Subscription>> {
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