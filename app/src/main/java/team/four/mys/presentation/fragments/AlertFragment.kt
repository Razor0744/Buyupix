package team.four.mys.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.databinding.FragmentAlertBinding
import team.four.mys.domain.models.Alert
import team.four.mys.extensions.appComponent
import team.four.mys.presentation.adapters.AlertAdapter
import team.four.mys.presentation.viewmodelsfragment.AlertViewModel
import javax.inject.Inject


class AlertFragment : Fragment() {

    @Inject
    lateinit var viewModel: AlertViewModel

    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlertBinding.inflate(inflater, container, false)

        requireContext().appComponent.injectAlertFragment(this)

        binding.buttonArrowLeft.setOnClickListener {
            findNavController().navigate(R.id.settings_fragment)
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
            AlertAdapter(
                alert,
                viewModel.getSettings()
            ) { alertClick ->
                viewModel.setSettings(value = alertClick.name)
                findNavController().navigate(R.id.settings_fragment)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterAlert
    }

    private companion object {
        val alert = listOf(
            Alert("The day before the write-off"),
            Alert("Two days before cancellation"),
            Alert("Three days before cancellation")
        )
    }
}