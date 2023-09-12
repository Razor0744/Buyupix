package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.databinding.FragmentCountryCodeBinding
import team.four.mys.domain.models.Country
import team.four.mys.presentation.adapters.CountryAdapter

class CountryCodeFragment : Fragment() {

    private var binding: FragmentCountryCodeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCountryCodeBinding.inflate(inflater, container, false)

        binding?.buttonArrowLeft?.setOnClickListener {
            findNavController().navigate(R.id.login_fragment)
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
                country = country
            ) {
                findNavController().navigate(R.id.login_fragment)
            }
        binding?.recyclerView?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerView?.adapter = adapterAlert
    }

    companion object {
        val country = listOf(
            Country(R.drawable.language_russia, "Russian Federation", "+7"),
            Country(R.drawable.language_usa, "USA", "+1"),
            Country(R.drawable.language_russia, "Belarus", "+375")
        )
    }
}