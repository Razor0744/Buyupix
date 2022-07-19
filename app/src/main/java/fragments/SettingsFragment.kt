package fragments

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import team.four.mys.AlertActivity
import team.four.mys.LanguageActivity
import team.four.mys.R
import team.four.mys.databinding.FragmentSettingsBinding
import kotlin.math.hypot

class SettingsFragment : Fragment() {

    private var binding: FragmentSettingsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSettingsBinding.inflate(inflater, container, false)

        onLoadDarkMode()

        binding?.alert?.setOnClickListener {
            startActivity(Intent(context, AlertActivity::class.java))
        }

        binding?.switchDarkMode?.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                //onSaveDarkMode(true)
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                revealDark()
            } else {
                //onSaveDarkMode(false)
                //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                revealLight()
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
        editor?.commit()
    }

    private fun onLoadDarkMode() {
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

        //text
        val colorText = resources.getColor(R.color.textMainDark, context?.theme)
        binding?.textSettings?.setTextColor(colorText)

        //items
        binding?.alert?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_dark, null)
        binding?.darkMode?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_dark, null)
        binding?.language?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_dark, null)
        binding?.about?.background =
            ResourcesCompat.getDrawable(resources, R.drawable.click_on_items_dark, null)

        val anim = ViewAnimationUtils.createCircularReveal(
            binding?.layout,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )
        anim.duration = 10000
        anim.start()
    }

    private fun revealLight() {
        val x: Int = binding?.layout!!.right
        val y: Int = binding?.layout!!.bottom

        val startRadius = 0

        val endRadius = hypot(
            binding?.layout?.width!!.toDouble(),
            binding?.layout?.height!!.toDouble()
        ).toInt()

        binding?.layout?.backgroundTintList = ColorStateList.valueOf(
            ResourcesCompat.getColor(
                resources,
                R.color.backgroundMain,
                null
            )
        )

        val color = resources.getColor(R.color.textMain, context?.theme)
        binding?.textSettings?.setTextColor(color)

        val anim = ViewAnimationUtils.createCircularReveal(
            binding?.layout,
            x,
            y,
            startRadius.toFloat(),
            endRadius.toFloat()
        )
        anim.duration = 1000
        anim.start()
    }
}