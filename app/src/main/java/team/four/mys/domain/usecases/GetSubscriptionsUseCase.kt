package team.four.mys.domain.usecases

import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository

class GetSubscriptionsUseCase(private val roomRepository: RoomRepository) {

    fun execute(): List<Subscription>{
        return roomRepository.getSubscriptions()
    }
}