package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.data.objects.ObjectsData
import team.four.mys.databinding.FragmentCountryCodeBinding
import team.four.mys.presentation.activity.AuthenticationActivity
import team.four.mys.presentation.adapters.CountryAdapter

class CountryCodeFragment : Fragment() {

    private var binding: FragmentCountryCodeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryCodeBinding.inflate(inflater, container, false)

        binding?.buttonArrowLeft?.setOnClickListener {
            (activity as AuthenticationActivity).replaceFragment(
                LoginFragment(),
                key = null,
                value = null
            )
        }

        adapter()

        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }

    private fun adapter() {
        val adapterAlert =
            CountryAdapter(
                ObjectsData.country
            ) { alertClick ->
                (activity as AuthenticationActivity).replaceFragment(
                    LoginFragment(),
                    key = "number",
                    value = alertClick.number
                )
            }
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = adapterAlert
    }
}