package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository
import javax.inject.Inject

class GetUIDUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun execute(): String {
        return firebaseRepository.getUID()
    }
}