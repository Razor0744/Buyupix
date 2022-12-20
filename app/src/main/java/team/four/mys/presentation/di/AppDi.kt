package team.four.mys.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.four.mys.domain.usecases.GetSubscriptionInfoUseCase
import team.four.mys.presentation.viewmodelsactivity.AlertViewModel
import team.four.mys.presentation.viewmodelsactivity.SubscriptionInfoViewModel

val appModule = module {

    viewModel<AlertViewModel> {
        AlertViewModel(
            getSettingsUseCase = get(),
            setSettingsUseCase = get()
        )
    }

    viewModel<SubscriptionInfoViewModel> {
        SubscriptionInfoViewModel(
            deleteSubscriptionUseCase = get(),
            getSubscriptionInfoUseCase = get()
        )
    }

}