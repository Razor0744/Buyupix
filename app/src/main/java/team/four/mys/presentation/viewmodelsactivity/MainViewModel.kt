package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.usecases.GetCategoryUseCase

class MainViewModel(
    private val getCategoryUseCase: GetCategoryUseCase
) : ViewModel() {

    suspend fun getCategory(category: String): Float {
        return getCategoryUseCase.execute(category = category).toFloat()
    }

}