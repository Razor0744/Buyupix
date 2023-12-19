package team.four.mys.domain.usecases

import team.four.mys.data.room.Subscription
import team.four.mys.domain.repository.FirebaseRepository
import javax.inject.Inject

class SetDataFirebaseUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun execute(uid: String, list: List<Subscription>) {
        firebaseRepository.setData(uid = uid, list = list)
    }
}