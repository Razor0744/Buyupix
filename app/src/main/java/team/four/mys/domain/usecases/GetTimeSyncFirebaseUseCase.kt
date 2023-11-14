package team.four.mys.domain.usecases

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.repository.FirebaseRepository

class GetTimeSyncFirebaseUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(uid: String): String? {
        return firebaseRepository.getTimeSync(uid = uid)
    }
}