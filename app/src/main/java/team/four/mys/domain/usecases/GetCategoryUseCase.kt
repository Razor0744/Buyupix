package team.four.mys.domain.usecases

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.repository.FirebaseRepository

class GetCategoryUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(category: String): Number {
        return firebaseRepository.getCategory(category = category)
    }
}