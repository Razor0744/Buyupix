package team.four.mys.domain.usecases

import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository

class GetSubscriptionInfoUseCase(private val roomRepository: RoomRepository) {

    fun execute(id: Long): Subscription {
        return roomRepository.getSubscriptionInfo(id = id)
    }
}