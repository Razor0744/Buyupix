package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.flow
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val getUIDUseCase: GetUIDUseCase,
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {

    fun getSubscriptions() = flow {
        emit(getSubscriptionsUseCase.execute())
    }

    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    fun getUID(): String {
        return getUIDUseCase.execute()
    }
}