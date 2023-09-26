package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import team.four.mys.R
import team.four.mys.databinding.FragmentCodeSendBinding
import team.four.mys.presentation.activity.MainActivity

class CodeSendFragment : Fragment() {

    private var _binding: FragmentCodeSendBinding? = null
    private val binding get() = _binding!!

    // firebase
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCodeSendBinding.inflate(inflater, container, false)

        // firebase auth
        auth = FirebaseAuth.getInstance()

        binding.code1.requestFocus()

        binding.buttonArrowLeft.setOnClickListener {
            findNavController().navigate(R.id.login_fragment)
        }

        textWatcher()


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun textWatcher() {
        binding.code1.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code2.requestFocus()
            }
        }
        binding.code2.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code3.requestFocus()
            } else {
                binding.code1.requestFocus()
            }
        }
        binding.code3.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code4.requestFocus()
            } else {
                binding.code2.requestFocus()
            }
        }
        binding.code4.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code5.requestFocus()
            } else {
                binding.code3.requestFocus()
            }
        }
        binding.code5.doAfterTextChanged {
            if (it?.length == 1) {
                binding.code6.requestFocus()
            } else {
                binding.code4.requestFocus()
            }
        }
        binding.code6.doAfterTextChanged {
            if (it?.length == 1) {
                val text = binding.code1.text.trim().toString() + binding.code2.text.trim()
                    .toString() + binding.code3.text.trim().toString() + binding.code4.text.trim()
                    .toString() + binding.code5.text.trim().toString() + binding.code6.text.trim()
                    .toString()
                (activity as MainActivity).verifyPhoneNumberWithCode(code = text)
            }
        }
    }
}