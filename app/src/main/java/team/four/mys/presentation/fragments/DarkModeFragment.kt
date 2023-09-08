package team.four.mys.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.databinding.FragmentDarkModeBinding
import team.four.mys.domain.models.DarkMode
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.presentation.activity.MainActivity
import team.four.mys.presentation.adapters.DarkModeAdapter
import team.four.mys.presentation.viewmodelsactivity.DarkModeViewModel

class DarkModeFragment : Fragment() {

    private var _binding: FragmentDarkModeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<DarkModeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDarkModeBinding.inflate(inflater, container, false)

        binding.buttonArrowLeft.setOnClickListener {
            (activity as MainActivity).replaceFragment(SettingsFragment())
        }

        adapter()

        return binding.root
    }

    private fun adapter() {
        val adapterDarkMode =
            DarkModeAdapter(
                darkMode,
                viewModel.getSettings(SettingsPreferencesParam(key = "DarkMode")).value
            ) { darkModeClick ->
                viewModel.setSettings(
                    SettingsPreferencesParam(
                        key = "DarkMode",
                        value = darkModeClick.name
                    )
                )
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterDarkMode
    }

    companion object {
        val darkMode = listOf(
            DarkMode("Dark Theme"),
            DarkMode("Light Theme"),
            DarkMode("System Theme")
        )
    }

}