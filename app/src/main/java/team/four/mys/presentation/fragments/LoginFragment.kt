package team.four.mys.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.InputType
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentLoginBinding
import team.four.mys.domain.models.SetNavigationBarParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.presentation.activity.AuthenticationActivity
import team.four.mys.presentation.activity.FirstActivity
import team.four.mys.presentation.other.SetNavigationColor
import team.four.mys.presentation.viewmodelsfragment.LoginViewModel


class LoginFragment : Fragment() {

    private var binding: FragmentLoginBinding? = null

    private val viewModel by viewModel<LoginViewModel>()

    private val countryCodes = arrayListOf("+7", "+375", "+1")
    private val countryName = arrayListOf("Russian Federation", "Belarus", "USA")
    private val countryNumberFormat = arrayListOf("000 000 0000", "00 000 0000", "000 000 0000")
    private var lengthCountryCode = 0

    // Timer
    private lateinit var mTimer: CountDownTimer

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Configure Phone Sign In
        binding?.loginCodeSent?.setOnClickListener {
            val phoneNumber = binding?.phoneNumber?.text.toString().trim()
            if (phoneNumber.isNotEmpty()) {
                (activity as AuthenticationActivity).setPhone(phoneNumber)
                (activity as AuthenticationActivity).startPhoneNumberVerification(phoneNumber)
            } else {
                Toast.makeText(requireContext(), "Please enter your phone", Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding?.buttonArrowLeft?.setOnClickListener {
            startActivity(Intent(requireContext(), FirstActivity::class.java))
        }

        binding?.countryCode?.setOnClickListener {
            (activity as AuthenticationActivity).replaceFragment(
                CountryCodeFragment(),
                key = null,
                value = null
            )
        }

        binding?.button1?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 1
            binding?.phoneNumber?.setText(text)
        }
        binding?.button2?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 2
            binding?.phoneNumber?.setText(text)
        }
        binding?.button3?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 3
            binding?.phoneNumber?.setText(text)
        }
        binding?.button4?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 4
            binding?.phoneNumber?.setText(text)
        }
        binding?.button5?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 5
            binding?.phoneNumber?.setText(text)
        }
        binding?.button6?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 6
            binding?.phoneNumber?.setText(text)
        }
        binding?.button7?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 7
            binding?.phoneNumber?.setText(text)
        }
        binding?.button8?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 8
            binding?.phoneNumber?.setText(text)
        }
        binding?.button9?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 9
            binding?.phoneNumber?.setText(text)
        }
        binding?.button0?.setOnClickListener {
            val text = binding?.phoneNumber?.text.toString() + 0
            binding?.phoneNumber?.setText(text)
        }
        binding?.button11?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    mTimer.start()
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    println("up")
                    mTimer.cancel()
                }
            }

            v?.onTouchEvent(event) ?: true
        }

        keyboardFalse()
        deleteChar()

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )
        SetNavigationColor().execute(
            SetNavigationBarParam(
                requireActivity(),
                ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun keyboardFalse() {
        binding?.phoneNumber?.setRawInputType(InputType.TYPE_NULL)
        binding?.phoneNumber?.requestFocus()
        textInputType()
        val number = arguments?.getString("number", null)
        if (number != null) {
            binding?.phoneNumber?.setText("$number")
        }
    }

    private fun textInputType() {
        binding?.phoneNumber?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var i = 0
                while (i <= 2) {
                    if (s.toString() == countryCodes[i]) {
                        var str = s.toString()
                        when (s?.length) {
                            4 -> {
                                str += "     "
                                lengthCountryCode = 4
                            }
                            3 -> {
                                str += "       "
                                lengthCountryCode = 3
                            }
                            2 -> {
                                str += "         "
                                lengthCountryCode = 2
                            }
                        }
                        binding?.phoneNumber?.setText(str)
                        binding?.phoneNumber?.setSelection(str.length)
                        setCountry(i)
                        setNumberFormat(i)
                        break
                    } else if (s?.length == 5) {
                        var str = s.toString()
                        str += "   "
                        binding?.phoneNumber?.setText(str)
                        binding?.phoneNumber?.setSelection(str.length)
                    } else if (s?.length!! < 6) {
                        if (lengthCountryCode > s.length) {
                            lengthCountryCode = s.length
                        }
                        if (s.toString().subSequence(0, lengthCountryCode) != countryCodes[i]) {
                            binding?.countryCodeText?.setText("")
                        }
                    }
                    i++
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
    }

    private fun setCountry(i: Int) {
        binding?.countryCodeText?.setText(countryName[i])
    }

    private fun setNumberFormat(i: Int) {
        binding?.numberFormat?.text = countryNumberFormat[i]
        binding?.numberFormat?.visibility = View.VISIBLE
    }

    private fun deleteChar() {
        mTimer = object : CountDownTimer(99999999999, 200) {
            override fun onTick(millisUntilFinished: Long) {
                if (binding?.phoneNumber?.text.toString().trim().length > 1) {
                    val text = binding?.phoneNumber?.text.toString().trim()
                        .substring(0, binding?.phoneNumber?.text.toString().trim().length - 1)
                    binding?.phoneNumber?.setText(text)
                }
            }

            override fun onFinish() {
            }
        }
    }
}