package team.four.mys.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import team.four.mys.data.api.retrofit.RetrofitClient
import team.four.mys.data.api.retrofit.currencies.Retrofit
import team.four.mys.data.database.Firebase
import team.four.mys.data.database.FirebaseDatabase
import team.four.mys.data.repository.FirebaseRepositoryImpl
import team.four.mys.data.repository.RoomRepositoryImpl
import team.four.mys.data.repository.SettingsRepositoryImpl
import team.four.mys.data.room.AppDatabase
import team.four.mys.data.room.SubscriptionDao
import team.four.mys.data.storage.SettingsPreferences
import team.four.mys.data.storage.SettingsStorage
import team.four.mys.domain.repository.FirebaseRepository
import team.four.mys.domain.repository.RoomRepository
import team.four.mys.domain.repository.SettingsRepository
import javax.inject.Singleton

@Module
object DataModule {

    @Provides
    fun provideSettingsRepository(settingsRepositoryImpl: SettingsRepositoryImpl): SettingsRepository {
        return settingsRepositoryImpl
    }

    @Provides
    fun provideSettingsRepositoryImpl(settingsStorage: SettingsStorage): SettingsRepositoryImpl {
        return SettingsRepositoryImpl(settingsStorage = settingsStorage)
    }

    @Provides
    fun provideSettingsPreferences(context: Context): SettingsPreferences {
        return SettingsPreferences(context = context)
    }

    @Provides
    fun provideSettingsStorage(settingsPreferences: SettingsPreferences): SettingsStorage {
        return settingsPreferences
    }

    @Provides
    fun provideFirebase(): Firebase {
        return Firebase()
    }

    @Provides
    fun provideFirebaseDatabase(firebase: Firebase): FirebaseDatabase {
        return firebase
    }

    @Provides
    fun provideFirebaseRepositoryImpl(firebaseDatabase: FirebaseDatabase): FirebaseRepositoryImpl {
        return FirebaseRepositoryImpl(firebaseDatabase = firebaseDatabase)
    }

    @Provides
    fun provideFirebaseRepository(firebaseRepositoryImpl: FirebaseRepositoryImpl): FirebaseRepository {
        return firebaseRepositoryImpl
    }

    @Provides
    fun provideRetrofit(retrofitClient: RetrofitClient): Retrofit {
        return Retrofit(retrofitClient = retrofitClient)
    }

    @Provides
    fun provideRetrofitClient(): RetrofitClient {
        return RetrofitClient
    }

    @Singleton
    @Provides
    fun provideRoomDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "database"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideSubscriptionDao(appDatabase: AppDatabase): SubscriptionDao {
        return appDatabase.getSubscriptionDao()
    }

    @Singleton
    @Provides
    fun provideRoomRepositoryImpl(subscriptionDao: SubscriptionDao): RoomRepositoryImpl {
        return RoomRepositoryImpl(subscriptionDao = subscriptionDao)
    }

    @Singleton
    @Provides
    fun provideRoomRepository(roomRepositoryImpl: RoomRepositoryImpl): RoomRepository {
        return roomRepositoryImpl
    }
}