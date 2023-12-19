package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository
import javax.inject.Inject

class SetTimeSyncFirebaseUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun execute(uid: String, time: String) {
        firebaseRepository.setTimeSync(uid = uid, time = time)
    }
}