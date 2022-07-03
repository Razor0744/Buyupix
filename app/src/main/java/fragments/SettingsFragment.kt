package fragments

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
import java.util.*

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding?.alert?.setOnClickListener {
            val bundle = arguments
            val alert = bundle?.getString("alert")
            val intent = Intent(context, AlertActivity::class.java)
            intent.putExtra("alert", alert)
            startActivity(intent)
        }

        binding?.switchDarkMode?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }
        }

        binding?.language?.setOnClickListener {
            val locale: String = Locale.getDefault().language
            val intent = Intent(context, LanguageActivity::class.java)
            intent.putExtra("locale", locale)
            startActivity(intent)
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}