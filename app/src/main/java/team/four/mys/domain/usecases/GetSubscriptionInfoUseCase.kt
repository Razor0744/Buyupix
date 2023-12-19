package team.four.mys.domain.usecases

import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository
import javax.inject.Inject

class GetSubscriptionInfoUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    fun execute(id: Long): Subscription {
        return roomRepository.getSubscriptionInfo(id = id)
    }
}