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
import team.four.mys.databinding.FragmentLanguageBinding
import team.four.mys.domain.models.Language
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.LocaleHelperUseCase
import team.four.mys.extensions.appComponent
import team.four.mys.presentation.activity.MainActivity
import team.four.mys.presentation.adapters.LanguageAdapter
import team.four.mys.presentation.viewmodelsfragment.LanguageViewModel
import javax.inject.Inject

class LanguageFragment : Fragment() {

    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!

    @Inject
     lateinit var viewModel: LanguageViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageBinding.inflate(inflater, container, false)

        requireContext().appComponent.injectLanguageFragment(this)

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
        val adapterLanguage =
            LanguageAdapter(
                language,
                viewModel.getSettings(SettingsPreferencesParam(key = "Locale"))
            ) { language ->
                when (language.name) {
                    "USA" -> LocaleHelperUseCase().setLocale(requireContext(), "en")
                    "Russia" -> LocaleHelperUseCase().setLocale(requireContext(), "ru")
                }
                startActivity(Intent(requireContext(), MainActivity::class.java))
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterLanguage
    }

    private companion object {
        val language = listOf(
            Language(R.drawable.language_usa, "USA"),
            Language(R.drawable.language_russia, "Russia")
        )
    }

}