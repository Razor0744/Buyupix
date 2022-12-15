package team.four.mys.data.storage

import team.four.mys.domain.models.SettingsPreferencesParam

interface SettingsStorage {

    fun setSettings(settingsPreferencesParam: SettingsPreferencesParam)

    fun getSettings(settingsPreferencesParam: SettingsPreferencesParam): SettingsPreferencesParam
}