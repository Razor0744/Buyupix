package team.four.mys.presentation.di

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
        DeleteSubscriptionUseCase(firebaseRepository = get())
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

}