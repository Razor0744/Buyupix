package team.four.mys.presentation.activity

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import team.four.mys.R
import team.four.mys.data.db.Preferences
import team.four.mys.databinding.ActivityMainBinding
import team.four.mys.presentation.other.LocaleHelper
import team.four.mys.presentation.fragments.HomeFragment
import team.four.mys.presentation.fragments.SettingsFragment
import team.four.mys.presentation.fragments.StatisticsFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //fragments
    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        Preferences.init(this)
        when (Preferences.getSettings("DarkMode")) {
            "System Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            "Dark Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            "Light Theme" -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
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
}