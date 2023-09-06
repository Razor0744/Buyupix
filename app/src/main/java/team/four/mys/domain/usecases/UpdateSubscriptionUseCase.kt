package team.four.mys.domain.usecases

import team.four.mys.domain.models.Subscription
import team.four.mys.domain.repository.RoomRepository

class UpdateSubscriptionUseCase(private val roomRepository: RoomRepository) {

    suspend fun execute(subscription: Subscription){
        roomRepository.updateSubscription(subscription = subscription)
    }
}