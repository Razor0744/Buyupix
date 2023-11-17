package team.four.mys.domain.usecases

import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Single
import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.RoomRepository

class GetSubscriptionsUseCase(private val roomRepository: RoomRepository) {

    fun execute(): Single<List<Subscription>> {
        return roomRepository.getSubscriptions()
    }
}