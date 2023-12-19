package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetTimeSyncFirebaseUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(uid: String): String? {
        return firebaseRepository.getTimeSync(uid = uid)
    }
}