package team.four.buyupix.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import team.four.buyupix.R
import team.four.buyupix.presentation.activity.AlertActivity
import team.four.buyupix.presentation.activity.DarkModeActivity
import team.four.buyupix.presentation.activity.LanguageActivity
import team.four.buyupix.databinding.FragmentSettingsBinding
import team.four.buyupix.domain.models.SetNavigationBarParam
import team.four.buyupix.domain.models.SetStatusBarParam
import team.four.buyupix.domain.usecases.SetNavigationBarUseCase
import team.four.buyupix.domain.usecases.SetStatusBarUseCase

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

        SetStatusBarUseCase().setStatusBar(
            SetStatusBarParam(
                requireContext(),
                requireActivity(),
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        SetNavigationBarUseCase().setNavigationBar(
            SetNavigationBarParam(
                requireActivity(),
                ResourcesCompat.getColor(resources, R.color.backgroundNavBar, null)
            )
        )

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}