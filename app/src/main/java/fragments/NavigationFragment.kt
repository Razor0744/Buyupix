package fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import androidx.fragment.app.Fragment
import team.four.mys.CalendarActivity
import team.four.mys.ProfileActivity
import team.four.mys.R
import team.four.mys.SubscriptionsActivity
import team.four.mys.databinding.FragmentNavigationBinding

class NavigationFragment : Fragment() {

    private var binding: FragmentNavigationBinding? = null

    @SuppressLint("UseCompatLoadingForDrawables")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNavigationBinding.inflate(inflater, container, false)

        //get argument
        val bundle = arguments
        val i = bundle?.getInt("i")

        //get resources
        val drawableHome = resources.getDrawable(R.drawable.ic_home_click, context?.theme)
        val drawableStatistics = resources.getDrawable(R.drawable.ic_statistics_click, context?.theme)
        val drawableSettings = resources.getDrawable(R.drawable.ic_settings_click, context?.theme)
        val color = resources.getColor(R.color.blue_200, context?.theme)
        val fontFamily = context?.let { ResourcesCompat.getFont(it, R.font.roboto_medium) }

        when (i) {
            1 -> home()
            2 -> statistics()
            3 -> settings()
        }

        when (i) {
            1 -> binding?.home?.setTextColor(color)
            2 -> binding?.statistics?.setTextColor(color)
            3 -> binding?.settings?.setTextColor(color)
        }

        when (i) {
            1 -> binding?.home?.setTypeface(fontFamily)
            2 -> binding?.statistics?.setTypeface(fontFamily)
            3 -> binding?.settings?.setTypeface(fontFamily)
        }

        when(i){
            1 -> binding?.home?.setCompoundDrawablesWithIntrinsicBounds(null, drawableHome, null, null)
            2 -> binding?.statistics?.setCompoundDrawablesWithIntrinsicBounds(null, drawableStatistics, null, null)
            3 -> binding?.settings?.setCompoundDrawablesWithIntrinsicBounds(null, drawableSettings, null, null)
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun home() {
        binding?.statisticsLayout?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }
        binding?.settingsLayout?.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private fun statistics() {
        binding?.homeLayout?.setOnClickListener {
            startActivity(Intent(context, SubscriptionsActivity::class.java))
        }
        binding?.settingsLayout?.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private fun settings() {
        binding?.homeLayout?.setOnClickListener {
            startActivity(Intent(context, SubscriptionsActivity::class.java))
        }
        binding?.statisticsLayout?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }
    }
}