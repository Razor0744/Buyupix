package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import team.four.mys.R
import team.four.mys.databinding.FragmentLoginBinding
import team.four.mys.domain.models.Country
import team.four.mys.presentation.activity.MainActivity


class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Configure Phone Sign In
        binding.continueButton.setOnClickListener {
            val phoneNumber = binding.countryButton.text.trim()
                .toString() + binding.phoneNumberEditText.text.toString().trim()
            (activity as MainActivity).setPhone(phoneNumber)
            (activity as MainActivity).startPhoneNumberVerification(phoneNumber)
        }

        binding.countryButton.setOnClickListener {
            findNavController().navigate(R.id.action_login_fragment_to_country_code_fragment)
        }

        setIconAndCode()

        return binding.root
    }

    private fun setIconAndCode() {
        val text =
            arguments?.getString("number")
        val drawable = when (text) {
            "+375" -> ResourcesCompat.getDrawable(resources, R.drawable.ic_belarus_small, null)
            "+1" -> ResourcesCompat.getDrawable(resources, R.drawable.ic_usa_small, null)
            "+7" -> ResourcesCompat.getDrawable(resources, R.drawable.ic_russia_small, null)
            else -> ResourcesCompat.getDrawable(resources, R.drawable.ic_belarus_small, null)
        }
        binding.countryButton.setCompoundDrawablesWithIntrinsicBounds(
            drawable,
            null,
            null,
            null
        )
        binding.countryButton.text = text
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}