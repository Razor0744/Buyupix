package team.four.mys.domain.usecases

import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository
import javax.inject.Inject

class GetSubscriptionsUseCase @Inject constructor(private val roomRepository: RoomRepository) {

    fun execute(): List<Subscription> {
        return roomRepository.getSubscriptions()
    }
}