package fragments

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import modelsRoom.DarkModeRoom
import room.AppDatabase
import team.four.mys.AlertActivity
import team.four.mys.LanguageActivity
import team.four.mys.R
import team.four.mys.databinding.FragmentSettingsBinding
import kotlin.concurrent.thread
import kotlin.math.hypot


class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null

    //Room
    private val databaseDarkMode by lazy { AppDatabase.getDatabase(requireContext()).darkModeDao() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        onLoadDarkModeSwitch()

        binding?.alert?.setOnClickListener {
            startActivity(Intent(context, AlertActivity::class.java))
        }

        binding?.switchDarkMode?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                CoroutineScope(Dispatchers.IO).launch { onSaveDarkMode(true) }
                //revealDark()
            } else {
                CoroutineScope(Dispatchers.IO).launch { onSaveDarkMode(false) }
                //revealLight()
            }
        }

        binding?.language?.setOnClickListener {
            startActivity(Intent(context, LanguageActivity::class.java))
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun onSaveDarkMode(boolean: Boolean) {
        val preferences = activity?.getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val editor = preferences?.edit()
        editor?.putBoolean("DarkMode", boolean)
        editor?.apply()
    }

    private fun onLoadDarkModeSwitch() {
        val preferences = activity?.getSharedPreferences("DarkMode", Context.MODE_PRIVATE)
        val darkMode = preferences?.getBoolean("DarkMode", false)
        if (darkMode == true) {
            binding?.switchDarkMode?.setOnCheckedChangeListener(null)
            binding?.switchDarkMode?.isChecked = true
        } else {
            binding?.switchDarkMode?.setOnCheckedChangeListener(null)
            binding?.switchDarkMode?.isChecked = false
        }
    }

    private fun revealDark() {
        val x: Int = binding?.switchDarkMode!!.right
        val y1: Int = binding?.darkMode!!.top
        val y2: Int = binding?.darkMode!!.bottom
        val y12: Int = (y2 - y1) / 2
        val y: Int = y1 + y12

        val startRadius = 0

        val endRadius = hypot(
            binding?.layout?.width!!.toDouble(),
            binding?.layout?.height!!.toDouble()
        ).toInt()

        //background
        binding?.layout?.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                R.color.backgroundMainDark,
                null
            )
        )

        //items//

        val colorText = resources.getColor(R.color.textMainDark, context?.theme)
        binding?.textSettings?.setTextColor(colorText)

        //alert
        binding?.alert?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_dark, null)
        val drawableAlert =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_alert_dark, context?.theme)
        binding?.alertText?.setCompoundDrawablesWithIntrinsicBounds(
            drawableAlert,
            null,
            null,
            null
        )
        binding?.alertText?.setTextColor(colorText)
        binding?.alertImage?.setImageResource(R.drawable.ic_arrow_right_dark)

        //darkMode
        binding?.darkMode?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_dark, null)
        val drawableDarkMode =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_dark_mode_dark, context?.theme)
        binding?.darkModeText?.setCompoundDrawablesWithIntrinsicBounds(
            drawableDarkMode,
            null,
            null,
            null
        )
        binding?.darkModeText?.setTextColor(colorText)

        //language
        binding?.language?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_dark, null)
        val drawableLanguage =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_language_dark, context?.theme)
        binding?.languageText?.setCompoundDrawablesWithIntrinsicBounds(
            drawableLanguage,
            null,
            null,
            null
        )
        binding?.languageText?.setTextColor(colorText)
        binding?.languageImage?.setImageResource(R.drawable.ic_arrow_right_dark)

        //about
        binding?.about?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_dark, null)
        val drawableAbout =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_alert_dark, context?.theme)
        binding?.aboutText?.setCompoundDrawablesWithIntrinsicBounds(
            drawableAbout,
            null,
            null,
            null
        )
        binding?.aboutText?.setTextColor(colorText)
        binding?.aboutImage?.setImageResource(R.drawable.ic_arrow_right_dark)

        //statusBar
        requireActivity().window.statusBarColor =
            requireContext().getColor(R.color.backgroundMainDark)
        requireActivity().window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE)

        //menu
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView?.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                R.color.backgroundNavBarDark,
                null
            )
        )
        val states = arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf())
        val colors = intArrayOf(
            resources.getColor(R.color.blueMain, context?.theme),
            resources.getColor(R.color.textSecondaryDark, context?.theme)
        )
        bottomNavigationView?.itemIconTintList = ColorStateList(states, colors)
        bottomNavigationView?.itemTextColor = ColorStateList(states, colors)
        bottomNavigationView?.itemBackground =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_navigation_dark, null)

        //animation
        val anim = ViewAnimationUtils.createCircularReveal(
            binding?.layout,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )
        anim.duration = 400
        anim.start()

    }

    private fun revealLight() {
        val x: Int = binding?.switchDarkMode!!.left
        val y1: Int = binding?.darkMode!!.top
        val y2: Int = binding?.darkMode!!.bottom
        val y12: Int = (y2 - y1) / 2
        val y: Int = y1 + y12

        val startRadius = 0

        val endRadius = hypot(
            binding?.layout?.width!!.toDouble(),
            binding?.layout?.height!!.toDouble()
        ).toInt()

        //background
        binding?.layout?.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                R.color.backgroundMainWhite,
                null
            )
        )

        //items//

        val colorText = resources.getColor(R.color.textMainWhite, context?.theme)
        binding?.textSettings?.setTextColor(colorText)

        //alert
        binding?.alert?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_white, null)
        val drawableAlert =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_alert_white, context?.theme)
        binding?.alertText?.setCompoundDrawablesWithIntrinsicBounds(
            drawableAlert,
            null,
            null,
            null
        )
        binding?.alertText?.setTextColor(colorText)
        binding?.alertImage?.setImageResource(R.drawable.ic_arrow_right_white)

        //darkMode
        binding?.darkMode?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_white, null)
        val drawableDarkMode =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_dark_mode_white, context?.theme)
        binding?.darkModeText?.setCompoundDrawablesWithIntrinsicBounds(
            drawableDarkMode,
            null,
            null,
            null
        )
        binding?.darkModeText?.setTextColor(colorText)

        //language
        binding?.language?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_white, null)
        val drawableLanguage =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_language_white, context?.theme)
        binding?.languageText?.setCompoundDrawablesWithIntrinsicBounds(
            drawableLanguage,
            null,
            null,
            null
        )
        binding?.languageText?.setTextColor(colorText)
        binding?.languageImage?.setImageResource(R.drawable.ic_arrow_right_white)

        //about
        binding?.about?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_white, null)
        val drawableAbout =
            ResourcesCompat.getDrawable(resources, R.drawable.ic_alert_white, context?.theme)
        binding?.aboutText?.setCompoundDrawablesWithIntrinsicBounds(
            drawableAbout,
            null,
            null,
            null
        )
        binding?.aboutText?.setTextColor(colorText)
        binding?.aboutImage?.setImageResource(R.drawable.ic_arrow_right_white)

        //statusBar
        requireActivity().window.statusBarColor =
            requireContext().getColor(R.color.backgroundMainWhite)
        requireActivity().window.decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR)

        //menu
        val bottomNavigationView =
            activity?.findViewById<BottomNavigationView>(R.id.bottomNavigation)
        bottomNavigationView?.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                R.color.backgroundNavBarWhite,
                null
            )
        )
        val states = arrayOf(intArrayOf(android.R.attr.state_checked), intArrayOf())
        val colors = intArrayOf(
            resources.getColor(R.color.blueMain, context?.theme),
            resources.getColor(R.color.textSecondaryWhite, context?.theme)
        )
        bottomNavigationView?.itemIconTintList = ColorStateList(states, colors)
        bottomNavigationView?.itemTextColor = ColorStateList(states, colors)
        bottomNavigationView?.itemBackground =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_navigation_white, null)

        //animation
        val anim = ViewAnimationUtils.createCircularReveal(
            binding?.layout,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )
        anim.duration = 400
        anim.start()
    }
}