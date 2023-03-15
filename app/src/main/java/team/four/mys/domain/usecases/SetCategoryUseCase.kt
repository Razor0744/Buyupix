package team.four.mys.domain.usecases

import team.four.mys.domain.repository.FirebaseRepository

class SetCategoryUseCase(private val firebaseRepository: FirebaseRepository) {

    fun execute(category: String, price: Double) {
        firebaseRepository.setCategory(category = category, price = price)
    }
}