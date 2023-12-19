package team.four.mys.di

import dagger.Module
import dagger.Provides
import team.four.mys.domain.usecases.AddSubscriptionUseCase
import team.four.mys.domain.usecases.CheckUserUseCase
import team.four.mys.domain.usecases.DeleteSubscriptionUseCase
import team.four.mys.domain.usecases.GetCategoryOfSubscriptionUseCase
import team.four.mys.domain.usecases.GetCurrencyIconUseCase
import team.four.mys.domain.usecases.GetSettingsUseCase
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase
import team.four.mys.domain.usecases.GetSubscriptionsUseCase
import team.four.mys.domain.usecases.GetUIDUseCase
import team.four.mys.domain.usecases.GetUrlImageUseCase
import team.four.mys.domain.usecases.SetNavigationColorUseCase
import team.four.mys.domain.usecases.SetSettingsUseCase
import team.four.mys.domain.usecases.SetStatusBarColorUseCase
import team.four.mys.domain.usecases.SetThemeUseCase
import team.four.mys.presentation.viewmodelsactivity.MainViewModel
import team.four.mys.presentation.viewmodelsfragment.AlertViewModel
import team.four.mys.presentation.viewmodelsfragment.CreateSubscriptionViewModel
import team.four.mys.presentation.viewmodelsfragment.DarkModeViewModel
import team.four.mys.presentation.viewmodelsfragment.HomeViewModel
import team.four.mys.presentation.viewmodelsfragment.LanguageViewModel
import team.four.mys.presentation.viewmodelsfragment.SubscriptionInfoViewModel

@Module
object AppModule {

    @Provides
    fun provideAlertViewModel(
        getSettingsUseCase: GetSettingsUseCase,
        setSettingsUseCase: SetSettingsUseCase
    ): AlertViewModel {
        return AlertViewModel(
            getSettingsUseCase = getSettingsUseCase,
            setSettingsUseCase = setSettingsUseCase
        )
    }

    @Provides
    fun provideSubscriptionInfoViewModel(
        getSubscriptionInfoUseCase: GetSubscriptionInfoUseCase,
        deleteSubscriptionUseCase: DeleteSubscriptionUseCase
    ): SubscriptionInfoViewModel {
        return SubscriptionInfoViewModel(
            getSubscriptionInfoUseCase = getSubscriptionInfoUseCase,
            deleteSubscriptionUseCase = deleteSubscriptionUseCase
        )
    }

    @Provides
    fun provideCreateSubscriptionViewModel(
        addSubscriptionUseCase: AddSubscriptionUseCase,
        getCategoryOfSubscriptionUseCase: GetCategoryOfSubscriptionUseCase,
        getUrlImageUseCase: GetUrlImageUseCase,
        getCurrencyIconUseCase: GetCurrencyIconUseCase
    ): CreateSubscriptionViewModel {
        return CreateSubscriptionViewModel(
            addSubscriptionUseCase = addSubscriptionUseCase,
            getCategoryOfSubscriptionUseCase = getCategoryOfSubscriptionUseCase,
            getUrlImageUseCase = getUrlImageUseCase,
            getCurrencyIconUseCase = getCurrencyIconUseCase
        )
    }

    @Provides
    fun provideDarkModeViewModel(
        getSettingsUseCase: GetSettingsUseCase,
        setSettingsUseCase: SetSettingsUseCase
    ): DarkModeViewModel {
        return DarkModeViewModel(
            getSettingsUseCase = getSettingsUseCase,
            setSettingsUseCase = setSettingsUseCase
        )
    }

    @Provides
    fun provideLanguageViewModel(getSettingsUseCase: GetSettingsUseCase): LanguageViewModel {
        return LanguageViewModel(getSettingsUseCase = getSettingsUseCase)
    }

    @Provides
    fun provideHomeViewModel(
        getUIDUseCase: GetUIDUseCase,
        getSubscriptionsUseCase: GetSubscriptionsUseCase
    ): HomeViewModel {
        return HomeViewModel(
            getUIDUseCase = getUIDUseCase,
            getSubscriptionsUseCase = getSubscriptionsUseCase
        )
    }

    @Provides
    fun provideMainViewModel(
        setNavigationColorUseCase: SetNavigationColorUseCase,
        setThemeUseCase: SetThemeUseCase,
        checkUserUseCase: CheckUserUseCase,
        setStatusBarColorUseCase: SetStatusBarColorUseCase
    ): MainViewModel {
        return MainViewModel(
            setNavigationColorUseCase = setNavigationColorUseCase,
            setThemeUseCase = setThemeUseCase,
            checkUserUseCase = checkUserUseCase,
            setStatusBarColorUseCase = setStatusBarColorUseCase
        )
    }
}