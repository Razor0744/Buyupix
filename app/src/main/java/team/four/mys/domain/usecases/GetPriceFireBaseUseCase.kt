package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository

class GetPriceFireBaseUseCase(private val firebaseRepository: FirebaseRepository) {

    suspend fun execute(string: String): Int {
        return firebaseRepository.getPriceFirebase(string = string)
    }
}
