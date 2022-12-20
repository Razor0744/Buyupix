package team.four.mys.presentation.di

import org.koin.dsl.module
import team.four.mys.data.database.Firebase
import team.four.mys.data.database.FirebaseDatabase
import team.four.mys.data.repository.FirebaseRepositoryImpl
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.data.storage.SettingsStorage
import team.four.mys.domain.repository.FirebaseRepository
import team.four.mys.domain.repository.SettingsRepository

val dataModule = module {

    single<SettingsStorage>{
        SettingsPreferences(context = get())
    }

    single<SettingsRepository>{
        SettingsRepositoryImpl(settingsStorage = get())
    }

    factory<FirebaseDatabase> {
        Firebase()
    }

    factory<FirebaseRepository> {
        FirebaseRepositoryImpl(firebaseDatabase = get())
    }
}