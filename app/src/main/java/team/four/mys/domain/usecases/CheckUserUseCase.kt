package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository

class CheckUserUseCase(private val firebaseRepository: FirebaseRepository) {

    fun execute(): Boolean {
        return firebaseRepository.checkUser()
    }
}