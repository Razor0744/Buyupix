package team.four.mys.domain.usecases

import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.repository.SettingsRepository

class GetSettingsUseCase(private val settingsRepository: SettingsRepository) {

    fun execute(settingsPreferencesParam: SettingsPreferencesParam): SettingsPreferencesParam{
        return settingsRepository.getSettings(settingsPreferencesParam)
    }
}