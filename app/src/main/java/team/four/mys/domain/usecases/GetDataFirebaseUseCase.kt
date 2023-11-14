package team.four.mys.domain.usecases

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.repository.FirebaseRepository

class GetDataFirebaseUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(uid: String): DocumentSnapshot?{
        return firebaseRepository.getData(uid = uid)
    }
}