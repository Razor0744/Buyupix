package team.four.mys.domain.repository

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import team.four.mys.data.room.Subscription

interface RoomRepository {

    fun getSubscriptions(): Single<List<Subscription>>

    fun getSubscriptionInfo(id: Long): Subscription

    fun addSubscription(subscription: Subscription)

    fun updateSubscription(subscription: Subscription)

    fun deleteSubscription(subscription: Subscription)
}