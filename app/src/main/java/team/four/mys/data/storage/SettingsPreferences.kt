package team.four.mys.data.storage

import android.content.Context
import team.four.mys.domain.models.SettingsPreferencesParam
import javax.inject.Inject

class SettingsPreferences @Inject constructor(context: Context): SettingsStorage {

    private val NAME = "Settings"
    private val MODE = Context.MODE_PRIVATE
    private var preferences = context.getSharedPreferences(NAME, MODE)

    override fun getSettings(settingsPreferencesParam: SettingsPreferencesParam): SettingsPreferencesParam {
        val value = preferences.getString(settingsPreferencesParam.key, null)
        return SettingsPreferencesParam(value = value)
    }

    override fun setSettings(settingsPreferencesParam: SettingsPreferencesParam) {
        preferences.edit().putString(settingsPreferencesParam.key, settingsPreferencesParam.value).apply()
    }
}