package team.four.mys.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.four.mys.presentation.other.SetStatusBarColor
import team.four.mys.presentation.viewmodelsactivity.*
import team.four.mys.presentation.viewmodelsfragment.*

val appModule = module {

    viewModel<AlertViewModel> {
        AlertViewModel(
            getSettingsUseCase = get(),
            setSettingsUseCase = get(),
            setStatusBarColor = get()
        )
    }

    viewModel<SubscriptionInfoViewModel> {
        SubscriptionInfoViewModel(
            deleteSubscriptionUseCase = get(),
            getSubscriptionInfoUseCase = get(),
            setStatusBarColor = get()
        )
    }

    viewModel<FirstActivityViewModel> {
        FirstActivityViewModel(
            setStatusBarColor = get()
        )
    }

    viewModel<CodeSendViewModel> {
        CodeSendViewModel(
            setStatusBarColor = get()
        )
    }

    viewModel<CreateSubscriptionViewModel> {
        CreateSubscriptionViewModel(
            setStatusBarColor = get(),
            getUIDUseCase = get()
        )
    }

    viewModel<DarkModeViewModel> {
        DarkModeViewModel(
            setStatusBarColor = get()
        )
    }

    viewModel<LanguageViewModel> {
        LanguageViewModel(
            setStatusBarColor = get()
        )
    }

    viewModel<AuthenticationViewModel> {
        AuthenticationViewModel()
    }

    viewModel<HomeViewModel> {
        HomeViewModel(
            setStatusBarColor = get(),
            retrofit = get(),
            getPriceFireBaseUseCase = get(),
            getUIDUseCase = get()
        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(
            setStatusBarColor = get()
        )
    }

    viewModel<StatisticsViewModel> {
        StatisticsViewModel(
            setStatusBarColor = get(),
            retrofit = get(),
            getPriceFireBaseUseCase = get()
        )
    }

    factory<SetStatusBarColor> {
        SetStatusBarColor(
            getSettingsUseCase = get()
        )
    }

    viewModel<LoginViewModel> {
        LoginViewModel(
            setStatusBarColor = get()
        )
    }

}