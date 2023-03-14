package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository

class GetNumberOfSubscriptionsUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(): Number {
        return firebaseRepository.getNumberOfSubscriptions()
    }
}