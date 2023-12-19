package team.four.mys.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import team.four.mys.presentation.activity.MainActivity
import team.four.mys.presentation.fragments.AlertFragment
import team.four.mys.presentation.fragments.CreateSubscriptionFragment
import team.four.mys.presentation.fragments.DarkModeFragment
import team.four.mys.presentation.fragments.HomeFragment
import team.four.mys.presentation.fragments.LanguageFragment
import team.four.mys.presentation.fragments.SubscriptionInfoFragment
import team.four.mys.presentation.viewmodelsactivity.MainViewModel
import team.four.mys.presentation.viewmodelsfragment.AlertViewModel
import team.four.mys.presentation.viewmodelsfragment.CreateSubscriptionViewModel
import team.four.mys.presentation.viewmodelsfragment.DarkModeViewModel
import team.four.mys.presentation.viewmodelsfragment.HomeViewModel
import team.four.mys.presentation.viewmodelsfragment.LanguageViewModel
import team.four.mys.presentation.viewmodelsfragment.SubscriptionInfoViewModel
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, DataModule::class, DomainModule::class])
interface AppComponent {

    fun injectAlertFragment(fragment: AlertFragment)
    fun injectCreateSubscriptionFragment(fragment: CreateSubscriptionFragment)
    fun injectDarkModeFragment(fragment: DarkModeFragment)
    fun injectHomeFragment(fragment: HomeFragment)
    fun injectLanguageFragment(fragment: LanguageFragment)
    fun injectSubscriptionInfoFragment(fragment: SubscriptionInfoFragment)
    fun injectMainActivity(activity: MainActivity)

    fun alertViewModel(): AlertViewModel
    fun createSubscriptionViewModel(): CreateSubscriptionViewModel
    fun darkModeViewModel(): DarkModeViewModel
    fun homeViewModel(): HomeViewModel
    fun languageViewModel(): LanguageViewModel
    fun subscriptionInfoViewModel(): SubscriptionInfoViewModel
    fun mainViewModel(): MainViewModel

    @Singleton
    @Component.Factory
    interface AppComponentFactory {
        fun create(@BindsInstance context: Context): AppComponent
    }
}