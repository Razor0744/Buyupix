package team.four.mys.domain.usecases

import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository
import javax.inject.Inject

class UpdateSubscriptionUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    fun execute(subscription: Subscription){
        roomRepository.updateSubscription(subscription = subscription)
    }
}