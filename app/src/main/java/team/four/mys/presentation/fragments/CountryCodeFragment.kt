package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.databinding.FragmentCountryCodeBinding
import team.four.mys.domain.models.Country
import team.four.mys.presentation.adapters.CountryAdapter

class CountryCodeFragment : Fragment() {

    private var _binding: FragmentCountryCodeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCountryCodeBinding.inflate(inflater, container, false)

        binding.buttonArrowLeft.setOnClickListener {
            findNavController().popBackStack()
        }

        adapter()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun adapter() {
        val adapterAlert =
            CountryAdapter(
                country = country
            ) {
                val bundle = bundleOf(
                    "number" to it.number
                )
                findNavController().navigate(R.id.login_fragment, bundle)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterAlert
    }

    private companion object {
        val country = listOf(
            Country(icon = R.drawable.ic_russia, name = "Russian Federation", number = "+7"),
            Country(icon = R.drawable.ic_usa, name = "USA", number = "+1"),
            Country(icon = R.drawable.ic_belarus, name = "Belarus", number = "+375")
        )
    }
}