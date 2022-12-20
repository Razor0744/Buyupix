package team.four.mys.presentation.di

import org.koin.dsl.module
import team.four.mys.domain.usecases.DeleteSubscriptionUseCase
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase

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
        DeleteSubscriptionUseCase()
    }

}