package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository

class SetNumberOfSubscriptionsUseCase(private val firebaseRepository: FirebaseRepository) {

    fun execute(){
        firebaseRepository.setNumberOfSubscriptions()
    }
}