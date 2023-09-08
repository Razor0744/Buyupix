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
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentSettingsBinding
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.presentation.activity.FirstActivity
import team.four.mys.presentation.activity.MainActivity
import team.four.mys.presentation.viewmodelsfragment.SettingsViewModel

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null

    private val viewModel by viewModel<SettingsViewModel>()

    private val auth: FirebaseAuth = Firebase.auth

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        binding?.alert?.setOnClickListener {
            (activity as MainActivity).replaceFragment(AlertFragment())
        }

        binding?.darkMode?.setOnClickListener {
            (activity as MainActivity).replaceFragment(DarkModeFragment())
        }

        binding?.language?.setOnClickListener {
            (activity as MainActivity).replaceFragment(LanguageFragment())
        }

        binding?.logOut?.setOnClickListener {
            auth.signOut()
            startActivity(Intent(context, FirstActivity::class.java))
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}