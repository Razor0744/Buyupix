package fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import team.four.mys.AlertActivity
import team.four.mys.LanguageActivity
import team.four.mys.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        onLoadDarkMode()

        binding?.alert?.setOnClickListener {
            startActivity(Intent(context, AlertActivity::class.java))
        }

        binding?.switchDarkMode?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                onSaveDarkMode(true)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                onSaveDarkMode(false)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
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
        editor?.commit()
    }

    private fun onLoadDarkMode() {
        val preferences = activity?.getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val darkMode = preferences?.getBoolean("DarkMode", false)
        if (darkMode == true) {
            binding?.switchDarkMode?.setOnCheckedChangeListener(null)
            binding?.switchDarkMode?.isChecked = true
        } else {
            binding?.switchDarkMode?.setOnCheckedChangeListener(null)
            binding?.switchDarkMode?.isChecked = false
        }
    }
}