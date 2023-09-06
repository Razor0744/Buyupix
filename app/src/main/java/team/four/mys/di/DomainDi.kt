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

    factory<GetSubscriptionInfoUseCase> {
        GetSubscriptionInfoUseCase(firebaseRepository = get())
    }

    factory<DeleteSubscriptionUseCase> {
        DeleteSubscriptionUseCase(roomRepository = get())
    }

    factory<GetPriceFireBaseUseCase> {
        GetPriceFireBaseUseCase(firebaseRepository = get())
    }

    factory<GetUIDUseCase> {
        GetUIDUseCase(firebaseRepository = get())
    }

    factory<GetNumberOfSubscriptionsUseCase> {
        GetNumberOfSubscriptionsUseCase(firebaseRepository = get())
    }

    factory<SetNumberOfSubscriptionsUseCase> {
        SetNumberOfSubscriptionsUseCase(firebaseRepository = get())
    }

    factory<SetCategoryUseCase> {
        SetCategoryUseCase(firebaseRepository = get())
    }

    factory<GetCategoryUseCase> {
        GetCategoryUseCase(firebaseRepository = get())
    }

    factory<CategoryOfSubscriptionUseCase> {
        CategoryOfSubscriptionUseCase()
    }

    factory<SetCategoryTotalPriceUseCase> {
        SetCategoryTotalPriceUseCase()
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
}