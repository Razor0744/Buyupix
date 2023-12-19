package team.four.mys.domain.usecases

import com.google.firebase.firestore.DocumentSnapshot
import team.four.mys.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetDataFirebaseUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(uid: String): DocumentSnapshot?{
        return firebaseRepository.getData(uid = uid)
    }
}