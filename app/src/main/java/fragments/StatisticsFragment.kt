package fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import team.four.mys.databinding.FragmentStatisticsBinding

class StatisticsFragment : Fragment() {

    private var binding: FragmentStatisticsBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        onLoadDarkMode()
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun onLoadDarkMode() {
        val preferences = activity?.getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val darkMode = preferences?.getBoolean("DarkMode", false)
        if (darkMode == true) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }
}