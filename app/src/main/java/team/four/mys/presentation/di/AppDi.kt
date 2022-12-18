package team.four.mys.presentation.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import team.four.mys.presentation.viewmodelsactivity.AlertViewModel

val appModule = module {

    viewModel<AlertViewModel> {
        AlertViewModel(
            getSettingsUseCase = get(),
            setSettingsUseCase = get()
        )
    }

}