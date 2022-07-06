package team.four.mys

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import ather.LocaleHelper
import fragments.HomeFragment
import fragments.SettingsFragment
import fragments.StatisticsFragment
import team.four.mys.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //fragments
    private val homeFragment = HomeFragment()
    private val statisticsFragment = StatisticsFragment()
    private val settingsFragment = SettingsFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
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
            "HomeFragment" -> binding.bottomNavigation.selectedItemId = R.id.home
            "StatisticsFragment" -> binding.bottomNavigation.selectedItemId = R.id.statistics
            "SettingsFragment" -> binding.bottomNavigation.selectedItemId = R.id.settings
            else -> binding.bottomNavigation.selectedItemId = R.id.home
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