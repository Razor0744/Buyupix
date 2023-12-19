package team.four.mys.domain.usecases

import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.repository.SettingsRepository
import javax.inject.Inject

class SetSettingsUseCase @Inject constructor(private val settingsRepository: SettingsRepository) {

    fun execute(settingsPreferencesParam: SettingsPreferencesParam) {
        settingsRepository.setSettings(settingsPreferencesParam)
    }
}