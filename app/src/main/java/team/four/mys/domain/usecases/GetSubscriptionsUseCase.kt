package team.four.mys.domain.usecases

import team.four.mys.domain.models.Subscription
import team.four.mys.domain.repository.RoomRepository

class GetSubscriptionsUseCase(private val roomRepository: RoomRepository) {

    suspend fun execute(): List<Subscription>{
        return roomRepository.getSubscriptions()
    }
}