package team.four.mys.presentation.fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.*
import android.text.style.ForegroundColorSpan
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
    private val countryNumberFormat = arrayListOf("0000000000", "000000000", "0000000000")
    private lateinit var countryNumberFormat2: String
    private var lengthCountryCode = 0

    // Timer
    private lateinit var mTimer: CountDownTimer

    private var textType = true

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentLoginBinding.inflate(inflater, container, false)

        // Configure Phone Sign In
        binding?.loginCodeSent?.setOnClickListener {
            val phoneNumber = binding?.phoneNumber1?.text.toString().trim() + binding?.phoneNumber2?.text.toString().trim()
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
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 1
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 1
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button2?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 2
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 2
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button3?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 3
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 3
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button4?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 4
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 4
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button5?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 5
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 5
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button6?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 6
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 6
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button7?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 7
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 7
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button8?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 8
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 8
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button9?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 9
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 9
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button0?.setOnClickListener {
            if (textType) {
                val text = binding?.phoneNumber1?.text.toString() + 0
                binding?.phoneNumber1?.text = text
            } else {
                val text = binding?.phoneNumber2?.text.toString() + 0
                binding?.phoneNumber2?.text = text
            }
        }
        binding?.button11?.setOnTouchListener { v, event ->
            when (event?.action) {
                MotionEvent.ACTION_DOWN -> {
                    v.isPressed = true
                    mTimer.start()
                    return@setOnTouchListener true
                }
                MotionEvent.ACTION_UP -> {
                    v.isPressed = false
                    mTimer.cancel()
                }
            }

            v?.onTouchEvent(event) ?: true
        }

        keyboardFalse()
        deleteChar()
        textChangeCountryNumberFormat()

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
        textChangeCountryCode()
        val number = arguments?.getString("number", null)
        if (number != null) {
            binding?.phoneNumber1?.text = number
        }
    }

    private fun textChangeCountryCode() {
        binding?.phoneNumber1?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                var i = 0
                while (i <= 2) {
                    if (s.toString() == countryCodes[i]) {
                        textType = false
                        setCountry(i)
                        setNumberFormat(i)
                        break
                    } else if (s?.length == 5) {
                        textType = false
                        break
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

    private fun textChangeCountryNumberFormat() {
        binding?.phoneNumber2?.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val text = SpannableString(countryNumberFormat2).apply {
                    setSpan(
                        ForegroundColorSpan(
                            ResourcesCompat.getColor(
                                resources,
                                R.color.backgroundMain,
                                null
                            )
                        ), 0, s.toString().length, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }
                binding?.numberFormat?.text = text
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
        countryNumberFormat2 = countryNumberFormat[i]
    }

    private fun deleteChar() {
        mTimer = object : CountDownTimer(99999999999, 200) {
            override fun onTick(millisUntilFinished: Long) {
                if (binding?.phoneNumber2?.text.toString().trim().isNotEmpty()) {
                    val text = binding?.phoneNumber2?.text.toString().trim()
                        .substring(0, binding?.phoneNumber2?.text.toString().trim().length - 1)
                    binding?.phoneNumber2?.text = text
                } else if (binding?.phoneNumber1?.text.toString().trim().length > 1) {
                    textType = true
                    val text = binding?.phoneNumber1?.text.toString().trim()
                        .substring(0, binding?.phoneNumber1?.text.toString().trim().length - 1)
                    binding?.phoneNumber1?.text = text
                }
            }

            override fun onFinish() {
            }
        }
    }
}