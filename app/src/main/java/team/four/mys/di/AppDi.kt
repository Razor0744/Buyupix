package team.four.mys.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.four.mys.presentation.viewmodelsactivity.AlertViewModel
import team.four.mys.presentation.viewmodelsactivity.AuthenticationViewModel
import team.four.mys.presentation.viewmodelsactivity.CreateSubscriptionViewModel
import team.four.mys.presentation.viewmodelsactivity.DarkModeViewModel
import team.four.mys.presentation.viewmodelsactivity.FirstActivityViewModel
import team.four.mys.presentation.viewmodelsactivity.LanguageViewModel
import team.four.mys.presentation.viewmodelsactivity.MainViewModel
import team.four.mys.presentation.viewmodelsactivity.SubscriptionInfoViewModel
import team.four.mys.presentation.viewmodelsfragment.CodeSendViewModel
import team.four.mys.presentation.viewmodelsfragment.HomeViewModel
import team.four.mys.presentation.viewmodelsfragment.LoginViewModel
import team.four.mys.presentation.viewmodelsfragment.SettingsViewModel
import team.four.mys.presentation.viewmodelsfragment.StatisticsViewModel

val appModule = module {

    viewModel<AlertViewModel> {
        AlertViewModel(
            getSettingsUseCase = get(),
            setSettingsUseCase = get(),
            setStatusBarColorUseCase = get(),
            setNavigationColorUseCase = get()
        )
    }

    viewModel<SubscriptionInfoViewModel> {
        SubscriptionInfoViewModel(
            getSubscriptionInfoUseCase = get(),
            setStatusBarColorUseCase = get()
        )
    }

    viewModel<FirstActivityViewModel> {
        FirstActivityViewModel(
            setStatusBarColorUseCase = get(),
            setThemeUseCase = get(),
            setNavigationColorUseCase = get()
        )
    }

    viewModel<CodeSendViewModel> {
        CodeSendViewModel(
            setStatusBarColorUseCase = get()
        )
    }

    viewModel<CreateSubscriptionViewModel> {
        CreateSubscriptionViewModel(
            setStatusBarColorUseCase = get(),
            retrofit = get(),
            addSubscriptionUseCase = get()
        )
    }

    viewModel<DarkModeViewModel> {
        DarkModeViewModel(
            setStatusBarColorUseCase = get()
        )
    }

    viewModel<LanguageViewModel> {
        LanguageViewModel(
            setStatusBarColorUseCase = get(),
            getSettingsUseCase = get()
        )
    }

    viewModel<AuthenticationViewModel> {
        AuthenticationViewModel()
    }

    viewModel<HomeViewModel> {
        HomeViewModel(
            setStatusBarColorUseCase = get(),
            retrofit = get(),
            getPriceFireBaseUseCase = get(),
            getUIDUseCase = get(),
            getSubscriptionsUseCase = get()
        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(
            setStatusBarColorUseCase = get()
        )
    }

    viewModel<StatisticsViewModel> {
        StatisticsViewModel(
            setStatusBarColorUseCase = get(),
            retrofit = get(),
            getPriceFireBaseUseCase = get(),
            getNumberOfSubscriptionsUseCase = get()
        )
    }

    viewModel<LoginViewModel> {
        LoginViewModel(
            setStatusBarColorUseCase = get()
        )
    }

    viewModel<MainViewModel> {
        MainViewModel(
            getCategoryUseCase = get(),
            setThemeUseCase = get()
        )
    }

}