package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import team.four.mys.data.room.Subscription
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class HomeViewModel(
    private val getUIDUseCase: GetUIDUseCase,
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {

    fun getSubscriptions(): Single<List<Subscription>> = getSubscriptionsUseCase.execute()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())


    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    fun getUID(): String {
        return getUIDUseCase.execute()
    }

}