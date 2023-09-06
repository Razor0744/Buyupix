package team.four.mys.domain.usecases

import team.four.mys.domain.models.Subscription
import team.four.mys.domain.repository.RoomRepository

class DeleteSubscriptionUseCase(private val roomRepository: RoomRepository) {

    suspend fun execute(subscription: Subscription) {
        roomRepository.deleteSubscription(subscription = subscription)
    }
}