package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository

class SetTimeSyncFirebaseUseCase(private val firebaseRepository: FirebaseRepository) {

    fun execute(uid: String, time: String) {
        firebaseRepository.setTimeSync(uid = uid, time = time)
    }
}