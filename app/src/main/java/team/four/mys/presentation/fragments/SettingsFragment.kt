package team.four.mys.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import team.four.mys.R
import team.four.mys.presentation.activity.AlertActivity
import team.four.mys.presentation.activity.DarkModeActivity
import team.four.mys.presentation.activity.LanguageActivity
import team.four.mys.databinding.FragmentSettingsBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.domain.usecases.SetNavigationBarUseCase
import team.four.mys.domain.usecases.SetStatusBarUseCase
import team.four.mys.presentation.activity.FirstActivity

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null

    private val auth: FirebaseAuth = Firebase.auth

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

        binding?.signOut?.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context, FirstActivity::class.java))
        }

        SetStatusBarUseCase().execute(
            SetStatusBarParam(
                requireContext(),
                requireActivity(),
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        SetNavigationBarUseCase().execute(
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