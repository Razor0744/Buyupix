package team.four.mys.domain.usecases

import team.four.mys.domain.models.Subscription
import team.four.mys.domain.repository.RoomRepository

class AddSubscriptionUseCase(private val roomRepository: RoomRepository) {

    suspend fun execute(subscription: Subscription){
        roomRepository.addSubscription(subscription = subscription)
    }
}