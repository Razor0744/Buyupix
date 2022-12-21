package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository

class GetUIDUseCase(private val firebaseRepository: FirebaseRepository) {

    fun execute(): String {
        return firebaseRepository.getUID()
    }
}