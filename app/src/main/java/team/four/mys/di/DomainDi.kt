package team.four.mys.di

import org.koin.dsl.module
import team.four.mys.domain.usecases.*

val domainModule = module {

    factory<GetSettingsUseCase> {
        GetSettingsUseCase(settingsRepository = get())
    }

    factory<SetSettingsUseCase> {
        SetSettingsUseCase(settingsRepository = get())
    }


    factory<DeleteSubscriptionUseCase> {
        DeleteSubscriptionUseCase(roomRepository = get())
    }


    factory<GetUIDUseCase> {
        GetUIDUseCase(firebaseRepository = get())
    }

    factory<GetCategoryOfSubscriptionUseCase> {
        GetCategoryOfSubscriptionUseCase()
    }

    factory<SetStatusBarColorUseCase> {
        SetStatusBarColorUseCase(
            getSettingsUseCase = get()
        )
    }

    factory<SetThemeUseCase> {
        SetThemeUseCase(getSettingsUseCase = get())
    }

    factory<SetNavigationColorUseCase> {
        SetNavigationColorUseCase()
    }

    factory<UpdateSubscriptionUseCase> {
        UpdateSubscriptionUseCase(roomRepository = get())
    }

    factory<AddSubscriptionUseCase> {
        AddSubscriptionUseCase(roomRepository = get())
    }

    factory<GetSubscriptionsUseCase> {
        GetSubscriptionsUseCase(roomRepository = get())
    }

    factory<GetSubscriptionInfoUseCase> {
        GetSubscriptionInfoUseCase(roomRepository = get())
    }

    factory<GetCurrencyIconUseCase> {
        GetCurrencyIconUseCase()
    }

    factory<GetUrlImageUseCase> {
        GetUrlImageUseCase()
    }

    factory<GetCategoryOfSubscriptionUseCase> {
        GetCategoryOfSubscriptionUseCase()
    }

    factory<DeleteSubscriptionUseCase> {
        DeleteSubscriptionUseCase(roomRepository = get())
    }

    factory<CheckUserUseCase> {
        CheckUserUseCase(firebaseRepository = get())
    }

    factory<GetTimeSyncFirebaseUseCase> {
        GetTimeSyncFirebaseUseCase(firebaseRepository = get())
    }

    factory<CheckTimeSyncUseCase> {
        CheckTimeSyncUseCase()
    }
}