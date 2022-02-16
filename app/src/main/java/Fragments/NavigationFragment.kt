package Fragments

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

        binding?.home?.setOnClickListener {
            startActivity(Intent(context, SubscriptionsActivity::class.java))
        }

        binding?.calendar?.setOnClickListener {
            startActivity(Intent(context, CalendarActivity::class.java))
        }

        binding?.plus?.setOnClickListener {
            startActivity(Intent(context, CreateSubscriptionActivity::class.java))
        }

        binding?.analytic?.setOnClickListener {
            startActivity(Intent(context, AnalyticActivity::class.java))
        }

        binding?.profile?.setOnClickListener {
            startActivity(Intent(context, ProfileActivity::class.java))
        }

        val bundle = arguments
        val drawable = resources.getDrawable(R.drawable.ic_home_click, context?.theme)
        val color = resources.getColor(R.color.blue_200, context?.theme)
        if (bundle != null) {
            when (bundle.getInt("i")) {
                1 -> binding?.home?.setTextColor(color)
                //2 -> binding?.calendar?.setTextColor(color)
                4 -> binding?.analytic?.setTextColor(color)
                5 -> binding?.profile?.setTextColor(color)
                1 -> binding?.home?.setCompoundDrawables(null, drawable, null, null)
                2 -> binding?.calendar?.setCompoundDrawables(null, drawable, null, null)
            }
        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}