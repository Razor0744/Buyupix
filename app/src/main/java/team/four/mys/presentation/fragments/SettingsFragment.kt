package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import team.four.mys.R
import team.four.mys.databinding.FragmentSettingsBinding

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
            findNavController().navigate(R.id.alert_fragment)
        }

        binding?.darkMode?.setOnClickListener {
            findNavController().navigate(R.id.dark_mode_fragment)
        }

        binding?.language?.setOnClickListener {
            findNavController().navigate(R.id.language_fragment)
        }

        binding?.logOut?.setOnClickListener {
            auth.signOut()
            findNavController().navigate(R.id.first_fragment)
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}