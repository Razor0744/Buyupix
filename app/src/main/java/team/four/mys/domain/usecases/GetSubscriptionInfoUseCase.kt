package team.four.mys.domain.usecases

import team.four.mys.domain.models.Subscription
import team.four.mys.domain.repository.RoomRepository

class GetSubscriptionInfoUseCase(private val roomRepository: RoomRepository) {

    suspend fun execute(id: Long): Subscription {
        return roomRepository.getSubscriptionInfo(id = id)
    }
}