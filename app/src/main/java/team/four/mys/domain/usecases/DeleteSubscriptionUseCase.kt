package team.four.mys.domain.usecases

import team.four.mys.domain.models.DeleteSubscriptionParam
import team.four.mys.domain.repository.FirebaseRepository

class DeleteSubscriptionUseCase(private val firebaseRepository: FirebaseRepository) {

    fun execute(deleteSubscriptionParam: DeleteSubscriptionParam) {
        firebaseRepository.deleteSubscription(deleteSubscriptionParam = deleteSubscriptionParam)
    }
}