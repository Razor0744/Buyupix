package team.four.mys.presentation.viewmodelsfragment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.CheckTimeSyncUseCase
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.GetTimeSyncFirebaseUseCase
import team.four.mys.domain.usecases.GetUIDUseCase

class SplashScreenViewModel(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val getTimeSyncFirebaseUseCase: GetTimeSyncFirebaseUseCase,
    private val getUIDUseCase: GetUIDUseCase,
    private val checkTimeSyncUseCase: CheckTimeSyncUseCase
) : ViewModel() {

    private var timeFirebaseLiveData = MutableLiveData<String?>()
    val timeFirebase: LiveData<String?> = timeFirebaseLiveData
    private var timeSettingsLiveData = MutableLiveData<String?>()
    val timeSettings: LiveData<String?> = timeSettingsLiveData
    private var checkTimeLiveData = MutableLiveData<Boolean>()
    val checkTime: LiveData<Boolean> = checkTimeLiveData

    fun getTimeSettings() {
        timeSettingsLiveData.postValue(
            getSettingsUseCase.execute(settingsPreferencesParam = SettingsPreferencesParam(key = "time")).value
        )
    }

    fun checkTimeSync(timeFirebase: String, timeSettings: String) {
        checkTimeLiveData.postValue(
            checkTimeSyncUseCase.execute(
                timeFirebase = timeFirebase,
                timeSettings = timeSettings
            )
        )
    }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            timeFirebaseLiveData.postValue(getTimeSyncFirebaseUseCase.execute(uid = getUIDUseCase.execute()))
        }
    }
}