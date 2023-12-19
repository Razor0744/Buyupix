package team.four.mys.data.repository

import team.four.mys.data.storage.SettingsStorage
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.repository.SettingsRepository
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(private val settingsStorage: SettingsStorage): SettingsRepository {

    override fun setSettings(settingsPreferencesParam: SettingsPreferencesParam) {
        settingsStorage.setSettings(settingsPreferencesParam)
    }

    override fun getSettings(settingsPreferencesParam: SettingsPreferencesParam): SettingsPreferencesParam{
        return settingsStorage.getSettings(settingsPreferencesParam)
    }
}