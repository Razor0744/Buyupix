package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Scheduler
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.observables.ConnectableObservable
import io.reactivex.rxjava3.schedulers.Schedulers
import team.four.mys.data.room.Subscription
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import java.util.concurrent.TimeUnit


class HomeViewModel(
    private val getUIDUseCase: GetUIDUseCase,
    private val getSubscriptionsUseCase: GetSubscriptionsUseCase
) : ViewModel() {

    //    private var subscriptionsLiveData = MutableLiveData<List<Subscription>>()
//    val subscriptions: LiveData<List<Subscription>> = subscriptionsLiveData

//    fun getSubscriptions(): Single<List<Subscription>> {
//        return getSubscriptionsUseCase.execute()
//            .subscribeOn(Schedulers.io())
//            .observeOn(Schedulers.single())
//    }

    val getSubscriptions: Single<List<Subscription>> = getSubscriptionsUseCase.execute()
        .subscribeOn(Schedulers.)
        .observeOn(AndroidSchedulers.mainThread())


    fun date(): String {
        return SimpleDateFormat("LLLL", Locale.getDefault()).format(Date())
    }

    fun getUID(): String {
        return getUIDUseCase.execute()
    }

}