package team.four.mys.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.four.mys.presentation.viewmodelsfragment.AlertViewModel
import team.four.mys.presentation.viewmodelsfragment.CreateSubscriptionViewModel
import team.four.mys.presentation.viewmodelsfragment.DarkModeViewModel
import team.four.mys.presentation.viewmodelsfragment.LanguageViewModel
import team.four.mys.presentation.viewmodelsactivity.MainViewModel
import team.four.mys.presentation.viewmodelsfragment.SubscriptionInfoViewModel
import team.four.mys.presentation.viewmodelsfragment.HomeViewModel
import team.four.mys.presentation.viewmodelsfragment.StatisticsViewModel

val appModule = module {

    viewModel<AlertViewModel> {
        AlertViewModel(
            getSettingsUseCase = get(),
            setSettingsUseCase = get()
        )
    }

    viewModel<SubscriptionInfoViewModel> {
        SubscriptionInfoViewModel(
            getSubscriptionInfoUseCase = get(),
            deleteSubscriptionUseCase = get()
        )
    }

    viewModel<CreateSubscriptionViewModel> {
        CreateSubscriptionViewModel(
            retrofit = get(),
            addSubscriptionUseCase = get(),
            getCategoryOfSubscriptionUseCase = get(),
            getCurrencyIconUseCase = get(),
            getUrlImageUseCase = get()
        )
    }

    viewModel<DarkModeViewModel> {
        DarkModeViewModel(
            setSettingsUseCase = get(),
            getSettingsUseCase = get()
        )
    }

    viewModel<LanguageViewModel> {
        LanguageViewModel(
            getSettingsUseCase = get()
        )
    }

    viewModel<HomeViewModel> {
        HomeViewModel(
            retrofit = get(),
            getUIDUseCase = get(),
            getSubscriptionsUseCase = get()
        )
    }

    viewModel<StatisticsViewModel> {
        StatisticsViewModel(
        )
    }

    viewModel<MainViewModel> {
        MainViewModel(
            setThemeUseCase = get(),
            checkUserUseCase = get(),
            setNavigationColorUseCase = get(),
            setStatusBarColorUseCase = get()
        )
    }

}