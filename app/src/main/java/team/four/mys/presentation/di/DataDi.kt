package team.four.mys.presentation.di

import org.koin.dsl.module
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.data.storage.SettingsStorage
import team.four.mys.domain.repository.SettingsRepository

val dataModule = module {

    single<SettingsStorage>{
        SettingsPreferences(context = get())
    }

    single<SettingsRepository>{
        SettingsRepositoryImpl(settingsStorage = get())
    }
}