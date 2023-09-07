package team.four.mys.presentation.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentAlertBinding
import team.four.mys.domain.models.Alert
import team.four.mys.domain.models.SetNavigationColorParam
import team.four.mys.domain.models.SetStatusBarParam
import team.four.mys.presentation.activity.MainActivity
import team.four.mys.presentation.adapters.AlertAdapter
import team.four.mys.presentation.viewmodelsfragment.AlertViewModel


class AlertFragment : Fragment() {

    private var _binding: FragmentAlertBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<AlertViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAlertBinding.inflate(inflater, container, false)

        binding.buttonArrowLeft.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            intent.putExtra("fragment", "SettingsFragment")
            startActivity(intent)
        }

        viewModel.setStatusBarColor(
            SetStatusBarParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

        viewModel.setNavigationColor(
            SetNavigationColorParam(
                activity = requireActivity(),
                color = ResourcesCompat.getColor(resources, R.color.backgroundMain, null)
            )
        )

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
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterAlert
    }

    companion object {
        val alert = listOf(
            Alert("The day before the write-off"),
            Alert("Two days before cancellation"),
            Alert("Three days before cancellation")
        )
    }
}