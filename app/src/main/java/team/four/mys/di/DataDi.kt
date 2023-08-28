package team.four.mys.di

import org.koin.dsl.module
import team.four.mys.data.api.retrofit.RetrofitClient
import team.four.mys.data.api.retrofit.currencies.Retrofit
import team.four.mys.data.database.Firebase
import team.four.mys.data.database.FirebaseDatabase
import team.four.mys.data.repository.FirebaseRepositoryImpl
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.data.storage.SettingsStorage
import team.four.mys.domain.repository.FirebaseRepository
import team.four.mys.domain.repository.SettingsRepository

val dataModule = module {

    single<SettingsStorage> {
        SettingsPreferences(context = get())
    }

    single<SettingsRepository> {
        SettingsRepositoryImpl(settingsStorage = get())
    }

    single<FirebaseDatabase> {
        Firebase()
    }

    single<FirebaseRepository> {
        FirebaseRepositoryImpl(firebaseDatabase = get())
    }

    single<Retrofit> {
        Retrofit(retrofitClient = get())
    }

    single<RetrofitClient> {
        RetrofitClient
    }
}