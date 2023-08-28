package team.four.mys.presentation.viewmodelsactivity

import androidx.lifecycle.ViewModel
import team.four.mys.domain.usecases.GetCategoryUseCase
import team.four.mys.domain.usecases.SetThemeUseCase

class MainViewModel(
    private val getCategoryUseCase: GetCategoryUseCase,
    private val setThemeUseCase: SetThemeUseCase
) : ViewModel() {

    suspend fun getCategory(category: String): Float {
        return getCategoryUseCase.execute(category = category).toFloat()
    }

    fun setTheme(){
        setThemeUseCase.execute()
    }

}