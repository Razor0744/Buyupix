package team.four.mys.domain.usecases

import org.junit.Assert
import org.junit.Test
import org.mockito.Mockito
import org.mockito.kotlin.mock
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.repository.SettingsRepository

class GetSettingsUseCaseTest {

    private val settingsRepository = mock<SettingsRepository>()

    @Test
    fun shouldReturnString() {

        Mockito.`when`(settingsRepository.getSettings(SettingsPreferencesParam())).thenReturn(
            SettingsPreferencesParam(value = "SystemTheme")
        )

        val useCase = GetSettingsUseCase(settingsRepository = settingsRepository)
        val expected = SettingsPreferencesParam(value = "SystemTheme")
        val actual =
            useCase.execute(settingsPreferencesParam = SettingsPreferencesParam())

        Assert.assertEquals(expected, actual)
    }
}