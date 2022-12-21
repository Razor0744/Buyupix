package team.four.mys.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.four.mys.presentation.other.SetStatusBarColor
import team.four.mys.presentation.viewmodelsactivity.*
import team.four.mys.presentation.viewmodelsfragment.HomeViewModel
import team.four.mys.presentation.viewmodelsfragment.SettingsViewModel
import team.four.mys.presentation.viewmodelsfragment.StatisticsViewModel

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

    viewModel<CodeSendActivityViewModel> {
        CodeSendActivityViewModel(setStatusBarColor = get())
    }

    viewModel<CreateSubscriptionViewModel> {
        CreateSubscriptionViewModel(setStatusBarColor = get())
    }

    viewModel<DarkModeViewModel> {
        DarkModeViewModel(setStatusBarColor = get())
    }

    viewModel<LanguageViewModel> {
        LanguageViewModel(setStatusBarColor = get())
    }

    viewModel<LoginViewModel> {
        LoginViewModel(setStatusBarColor = get())
    }

    viewModel<HomeViewModel> {
        HomeViewModel(
            setStatusBarColor = get(),
            retrofit = get(),
            getPriceFireBaseUseCase = get()
        )
    }

    viewModel<SettingsViewModel> {
        SettingsViewModel(setStatusBarColor = get())
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

}