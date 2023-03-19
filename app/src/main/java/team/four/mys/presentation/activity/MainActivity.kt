package team.four.mys.presentation.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.ActivityMainBinding
import team.four.mys.presentation.other.SetTheme
import team.four.mys.presentation.fragments.HomeFragment
import team.four.mys.presentation.fragments.SettingsFragment
import team.four.mys.presentation.fragments.StatisticsFragment
import team.four.mys.presentation.other.LocaleHelper
import team.four.mys.presentation.viewmodelsactivity.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModel<MainViewModel>()

    //Fragments
    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val settingsFragment = SettingsFragment()

    //Categories
    var gamingPrice = 20f
    var defencePrice = 20f
    var cloudPrice = 230f
    var moviesPrice = 230f
    var booksPrice = 230f
    var musicPrice = 199f
    var otherPrice = 12.99f

    override fun onCreate(savedInstanceState: Bundle?) {
        SetTheme(context = applicationContext).execute()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.home -> {
                    replaceFragment(homeFragment)
                }
                R.id.statistics -> {
                    replaceFragment(statisticsFragment)
                }
                R.id.settings -> {
                    replaceFragment(settingsFragment)
                }
            }
            true
        }

        when (intent.getStringExtra("fragment")) {
            "HomeFragment" -> {
                binding.bottomNavigation.selectedItemId = R.id.home
                replaceFragment(homeFragment)
            }
            "StatisticsFragment" -> {
                binding.bottomNavigation.selectedItemId = R.id.statistics
                replaceFragment(statisticsFragment)
            }
            "SettingsFragment" -> {
                binding.bottomNavigation.selectedItemId = R.id.settings
                replaceFragment(settingsFragment)
            }
            else -> {
                binding.bottomNavigation.selectedItemId = R.id.home
                replaceFragment(homeFragment)
            }
        }

        CoroutineScope(Dispatchers.IO).launch {
            getCategories()
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.frame, fragment)
        transaction.commit()
    }

    override fun attachBaseContext(base: Context) {
        LocaleHelper().setLocale(base, LocaleHelper().getLanguage(base))
        super.attachBaseContext(LocaleHelper().onAttach(base))
    }

    private suspend fun getCategories() {
        gamingPrice = viewModel.getCategory("Gaming")
        defencePrice = viewModel.getCategory("Defence")
        cloudPrice = viewModel.getCategory("Cloud")
        moviesPrice = viewModel.getCategory("Movies")
        booksPrice = viewModel.getCategory("Books")
        musicPrice = viewModel.getCategory("Music")
        otherPrice = viewModel.getCategory("Other")
    }

}