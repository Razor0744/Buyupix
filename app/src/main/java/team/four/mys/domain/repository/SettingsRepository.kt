package team.four.mys.domain.repository

import team.four.mys.domain.models.SettingsPreferencesParam

interface SettingsRepository {

    fun setSettings(settingsPreferencesParam: SettingsPreferencesParam)

    fun getSettings(settingsPreferencesParam: SettingsPreferencesParam): SettingsPreferencesParam
}