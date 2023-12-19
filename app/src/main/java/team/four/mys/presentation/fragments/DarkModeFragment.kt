package team.four.mys.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import team.four.mys.R
import team.four.mys.databinding.FragmentDarkModeBinding
import team.four.mys.domain.models.DarkMode
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.extensions.appComponent
import team.four.mys.presentation.activity.MainActivity
import team.four.mys.presentation.adapters.DarkModeAdapter
import team.four.mys.presentation.viewmodelsfragment.DarkModeViewModel
import javax.inject.Inject

class DarkModeFragment : Fragment() {

    private var _binding: FragmentDarkModeBinding? = null
    private val binding get() = _binding!!

    @Inject
     lateinit var viewModel: DarkModeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDarkModeBinding.inflate(inflater, container, false)

        requireContext().appComponent.injectDarkModeFragment(this)

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
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterDarkMode
    }

    private companion object {
        val darkMode = listOf(
            DarkMode("Dark Theme"),
            DarkMode("Light Theme"),
            DarkMode("System Theme")
        )
    }

}