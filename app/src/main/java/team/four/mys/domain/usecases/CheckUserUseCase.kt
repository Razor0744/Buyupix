package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository
import javax.inject.Inject

class CheckUserUseCase @Inject constructor(private val firebaseRepository: FirebaseRepository) {

    fun execute(): Boolean {
        return firebaseRepository.checkUser()
    }
}