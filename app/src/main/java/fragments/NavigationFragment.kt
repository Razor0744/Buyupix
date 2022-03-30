package fragments

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import team.four.mys.*
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
        val drawableCalendar = resources.getDrawable(R.drawable.ic_calendar_click, context?.theme)
        val drawableCreateSubscription = resources.getDrawable(R.drawable.ic_create_subscription_click, context?.theme)
        val drawableAnalytic = resources.getDrawable(R.drawable.ic_analytic_click, context?.theme)
        val drawableProfile = resources.getDrawable(R.drawable.ic_profile_click, context?.theme)
        val color = resources.getColor(R.color.blue_200, context?.theme)

        when (i) {
            1 -> home()
            2 -> calendar()
            3 -> createSubscription()
            4 -> analytic()
            5 -> profile()
        }

        when (i) {
            1 -> binding?.home?.setTextColor(color)
            2 -> binding?.calendar?.setTextColor(color)
            4 -> binding?.analytic?.setTextColor(color)
            5 -> binding?.profile?.setTextColor(color)
        }

        when(i){
            1 -> binding?.home?.setCompoundDrawablesWithIntrinsicBounds(null, drawableHome, null, null)
            2 -> binding?.calendar?.setCompoundDrawablesWithIntrinsicBounds(null, drawableCalendar, null, null)
            3 -> binding?.createSubscription?.setImageDrawable(drawableCreateSubscription)
            4 -> binding?.analytic?.setCompoundDrawablesWithIntrinsicBounds(null, drawableAnalytic, null, null)
            5 -> binding?.profile?.setCompoundDrawablesWithIntrinsicBounds(null, drawableProfile, null, null)
        }

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun home() {
        binding?.calendar?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }
        binding?.createSubscription?.setOnClickListener {
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }
        binding?.analytic?.setOnClickListener {
            startActivity(Intent(context, AnalyticActivity::class.java))
        }
        binding?.profile?.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private fun calendar() {
        binding?.home?.setOnClickListener {
            startActivity(Intent(context, SubscriptionsActivity::class.java))
        }
        binding?.createSubscription?.setOnClickListener {
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }
        binding?.analytic?.setOnClickListener {
            startActivity(Intent(context, AnalyticActivity::class.java))
        }
        binding?.profile?.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private fun createSubscription() {
        binding?.home?.setOnClickListener {
            startActivity(Intent(context, SubscriptionsActivity::class.java))
        }
        binding?.calendar?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }
        binding?.createSubscription?.setOnClickListener {
            startActivity(Intent(context, SubscriptionsActivity::class.java))
        }
        binding?.analytic?.setOnClickListener {
            startActivity(Intent(context, AnalyticActivity::class.java))
        }
        binding?.profile?.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private fun analytic() {
        binding?.home?.setOnClickListener {
            startActivity(Intent(context, SubscriptionsActivity::class.java))
        }
        binding?.calendar?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }
        binding?.createSubscription?.setOnClickListener {
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }
        binding?.profile?.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }
    }

    private fun profile() {
        binding?.home?.setOnClickListener {
            startActivity(Intent(context, SubscriptionsActivity::class.java))
        }
        binding?.calendar?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }
        binding?.createSubscription?.setOnClickListener {
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }
        binding?.analytic?.setOnClickListener {
            startActivity(Intent(context, AnalyticActivity::class.java))
        }
    }
}