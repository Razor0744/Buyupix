package team.four.mys.presentation.fragments

import android.os.Bundle
import android.os.CountDownTimer
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.*
import team.four.mys.R
import team.four.mys.databinding.FragmentCodeSendBinding
import team.four.mys.presentation.activity.MainActivity

class CodeSendFragment : Fragment() {

    private var binding: FragmentCodeSendBinding? = null

    // firebase
    private lateinit var auth: FirebaseAuth
    private var codeNumber: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCodeSendBinding.inflate(inflater, container, false)

        // firebase auth
        auth = FirebaseAuth.getInstance()

        binding?.code1?.requestFocus()

        binding?.buttonArrowLeft?.setOnClickListener {
            findNavController().navigate(R.id.login_fragment)
        }

        binding?.button1?.setOnClickListener {
            setPhoneNumber("1")
            focused("1")
        }
        binding?.button2?.setOnClickListener {
            setPhoneNumber("2")
            focused("2")
        }
        binding?.button3?.setOnClickListener {
            setPhoneNumber("3")
            focused("3")
        }
        binding?.button4?.setOnClickListener {
            setPhoneNumber("4")
            focused("4")
        }
        binding?.button5?.setOnClickListener {
            setPhoneNumber("5")
            focused("5")
        }
        binding?.button6?.setOnClickListener {
            setPhoneNumber("6")
            focused("6")
        }
        binding?.button7?.setOnClickListener {
            setPhoneNumber("7")
            focused("7")
        }
        binding?.button8?.setOnClickListener {
            setPhoneNumber("8")
            focused("8")
        }
        binding?.button9?.setOnClickListener {
            setPhoneNumber("9")
            focused("9")
        }
        binding?.button0?.setOnClickListener {
            setPhoneNumber("0")
            focused("0")
        }
        binding?.button11?.setOnClickListener {
            focused("11")
            if (codeNumber.trim().isNotEmpty()) {
                codeNumber = codeNumber.substring(0, codeNumber.length - 1)
            }
        }

        inputType()
        textWatcher()
        timer()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun inputType() {
        binding?.code1?.setRawInputType(InputType.TYPE_NULL)
        binding?.code2?.setRawInputType(InputType.TYPE_NULL)
        binding?.code3?.setRawInputType(InputType.TYPE_NULL)
        binding?.code4?.setRawInputType(InputType.TYPE_NULL)
        binding?.code5?.setRawInputType(InputType.TYPE_NULL)
        binding?.code6?.setRawInputType(InputType.TYPE_NULL)
    }

    private fun setPhoneNumber(number: String) {
        codeNumber += number
    }

    private fun focused(number: String) {
        if (number != "11") {
            when (requireActivity().currentFocus) {
                binding?.code1 -> binding?.code1?.setText(number)
                binding?.code2 -> binding?.code2?.setText(number)
                binding?.code3 -> binding?.code3?.setText(number)
                binding?.code4 -> binding?.code4?.setText(number)
                binding?.code5 -> binding?.code5?.setText(number)
                binding?.code6 -> {
                    binding?.code6?.setText(number)
                    (activity as MainActivity).verifyPhoneNumberWithCode(code = codeNumber)
                }
            }
        } else {
            when (requireActivity().currentFocus) {
                binding?.code2 -> {
                    binding?.code1?.text = null
                    binding?.code1?.requestFocus()
                }

                binding?.code3 -> {
                    binding?.code2?.text = null
                    binding?.code2?.requestFocus()
                }

                binding?.code4 -> {
                    binding?.code3?.text = null
                    binding?.code3?.requestFocus()
                }

                binding?.code5 -> {
                    binding?.code4?.text = null
                    binding?.code4?.requestFocus()
                }

                binding?.code6 -> {
                    binding?.code5?.text = null
                    binding?.code5?.requestFocus()
                }
            }
        }
    }

    private fun textWatcher() {
        binding?.code1?.doAfterTextChanged {
            if (it?.length == 1) {
                binding?.code2?.requestFocus()
            }
        }
        binding?.code2?.doAfterTextChanged {
            if (it?.length == 1) {
                binding?.code3?.requestFocus()
            }
        }
        binding?.code3?.doAfterTextChanged {
            if (it?.length == 1) {
                binding?.code4?.requestFocus()
            }
        }
        binding?.code4?.doAfterTextChanged {
            if (it?.length == 1) {
                binding?.code5?.requestFocus()
            }
        }
        binding?.code5?.doAfterTextChanged {
            if (it?.length == 1) {
                binding?.code6?.requestFocus()
            }
        }
    }

    private fun timer() {
        var counter = 60
        object : CountDownTimer(60000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                binding?.timer?.text = counter.toString()
                counter--
            }

            override fun onFinish() {
                binding?.textTimer?.text = "Для повторной отправки нажмите "
                binding?.timer?.text = "здесь"
                binding?.timer?.setOnClickListener {
                    (activity as MainActivity).resendVerificationCode()
                }
            }
        }.start()
    }
}