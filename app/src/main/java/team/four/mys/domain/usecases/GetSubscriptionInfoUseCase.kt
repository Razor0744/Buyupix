package team.four.mys.domain.usecases

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.models.SubscriptionInfoParam
import team.four.mys.domain.repository.FirebaseRepository

class GetSubscriptionInfoUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(subscriptionInfoParam: SubscriptionInfoParam): DocumentSnapshot {
        return firebaseRepository.getSubscriptionInfo(subscriptionInfoParam = subscriptionInfoParam)
    }
}