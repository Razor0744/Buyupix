package team.four.mys.di

import dagger.Module
import dagger.Provides
import team.four.mys.domain.repository.FirebaseRepository
import team.four.mys.domain.repository.RoomRepository
import team.four.mys.domain.repository.SettingsRepository
import team.four.mys.domain.usecases.AddSubscriptionUseCase
import team.four.mys.domain.usecases.CheckTimeSyncUseCase
import team.four.mys.domain.usecases.CheckUserUseCase
import team.four.mys.domain.usecases.DeleteSubscriptionUseCase
import team.four.mys.domain.usecases.GetCategoryOfSubscriptionUseCase
import team.four.mys.domain.usecases.GetCurrencyIconUseCase
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetTimeSyncFirebaseUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import team.four.mys.domain.usecases.GetUrlImageUseCase
import team.four.mys.domain.usecases.SetNavigationColorUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase
import team.four.mys.domain.usecases.SetThemeUseCase
import team.four.mys.domain.usecases.UpdateSubscriptionUseCase

@Module
object DomainModule {

    @Provides
    fun provideGetSettingsUseCase(settingsRepository: SettingsRepository): GetSettingsUseCase {
        return GetSettingsUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideSetSettingsUseCase(settingsRepository: SettingsRepository): SetSettingsUseCase {
        return SetSettingsUseCase(settingsRepository = settingsRepository)
    }

    @Provides
    fun provideGetUIDUseCase(firebaseRepository: FirebaseRepository): GetUIDUseCase {
        return GetUIDUseCase(firebaseRepository = firebaseRepository)
    }

    @Provides
    fun provideGetCategoryOfSubscriptionUseCase(): GetCategoryOfSubscriptionUseCase {
        return GetCategoryOfSubscriptionUseCase()
    }

    @Provides
    fun provideSetStatusBarColorUseCase(getSettingsUseCase: GetSettingsUseCase): SetStatusBarColorUseCase {
        return SetStatusBarColorUseCase(getSettingsUseCase = getSettingsUseCase)
    }

    @Provides
    fun provideSetThemeUseCase(getSettingsUseCase: GetSettingsUseCase): SetThemeUseCase {
        return SetThemeUseCase(getSettingsUseCase = getSettingsUseCase)
    }

    @Provides
    fun provideSetNavigationColorUseCase(): SetNavigationColorUseCase {
        return SetNavigationColorUseCase()
    }

    @Provides
    fun provideGetCurrencyIconUseCase(): GetCurrencyIconUseCase {
        return GetCurrencyIconUseCase()
    }

    @Provides
    fun provideGetUrlImageUseCase(): GetUrlImageUseCase {
        return GetUrlImageUseCase()
    }

    @Provides
    fun provideCheckUserUseCase(firebaseRepository: FirebaseRepository): CheckUserUseCase {
        return CheckUserUseCase(firebaseRepository = firebaseRepository)
    }

    @Provides
    fun provideGetTimeSyncFirebaseUseCase(firebaseRepository: FirebaseRepository): GetTimeSyncFirebaseUseCase {
        return GetTimeSyncFirebaseUseCase(firebaseRepository = firebaseRepository)
    }

    @Provides
    fun provideCheckTimeSyncUseCase(): CheckTimeSyncUseCase {
        return CheckTimeSyncUseCase()
    }

    @Provides
    fun provideDeleteSubscriptionUseCase(roomRepository: RoomRepository): DeleteSubscriptionUseCase {
        return DeleteSubscriptionUseCase(roomRepository = roomRepository)
    }

    @Provides
    fun provideUpdateSubscriptionUseCase(roomRepository: RoomRepository): UpdateSubscriptionUseCase {
        return UpdateSubscriptionUseCase(roomRepository = roomRepository)
    }

    @Provides
    fun provideAddSubscriptionUseCase(roomRepository: RoomRepository): AddSubscriptionUseCase {
        return AddSubscriptionUseCase(roomRepository = roomRepository)
    }

    @Provides
    fun provideGetSubscriptionsUseCase(roomRepository: RoomRepository): GetSubscriptionsUseCase {
        return GetSubscriptionsUseCase(roomRepository = roomRepository)
    }

    @Provides
    fun provideGetSubscriptionInfoUseCase(roomRepository: RoomRepository): GetSubscriptionInfoUseCase {
        return GetSubscriptionInfoUseCase(roomRepository = roomRepository)
    }

}