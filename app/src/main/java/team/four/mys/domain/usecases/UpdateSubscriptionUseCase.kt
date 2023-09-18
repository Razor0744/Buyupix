package team.four.mys.domain.usecases

import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository

class UpdateSubscriptionUseCase(private val roomRepository: RoomRepository) {

    fun execute(subscription: Subscription){
        roomRepository.updateSubscription(subscription = subscription)
    }
}