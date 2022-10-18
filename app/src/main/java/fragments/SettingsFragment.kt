package fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import team.four.mys.presentation.AlertActivity
import team.four.mys.presentation.DarkModeActivity
import team.four.mys.presentation.LanguageActivity
import team.four.mys.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding?.alert?.setOnClickListener {
            startActivity(Intent(context, AlertActivity::class.java))
        }

        binding?.darkMode?.setOnClickListener {
            startActivity(Intent(context, DarkModeActivity::class.java))
        }

        binding?.language?.setOnClickListener {
            startActivity(Intent(context, LanguageActivity::class.java))
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun onSaveDarkMode(boolean: Boolean) {
        val preferences = activity?.getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val editor = preferences?.edit()
        editor?.putBoolean("DarkMode", boolean)
        editor?.apply()
    }
}