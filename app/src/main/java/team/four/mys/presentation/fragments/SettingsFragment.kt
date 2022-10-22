package team.four.mys.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import team.four.mys.R
import team.four.mys.presentation.AlertActivity
import team.four.mys.presentation.DarkModeActivity
import team.four.mys.presentation.LanguageActivity
import team.four.mys.databinding.FragmentSettingsBinding
import team.four.mys.domain.usecases.SetStatusBarUseCase

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

        SetStatusBarUseCase().execute(
            requireContext(),
            requireActivity(),
            requireContext().getColor(R.color.backgroundMain)
        )

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}