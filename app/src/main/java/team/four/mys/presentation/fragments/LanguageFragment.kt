package team.four.mys.presentation.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import team.four.mys.R
import team.four.mys.databinding.FragmentLanguageBinding
import team.four.mys.domain.models.Language
import team.four.mys.domain.models.SettingsPreferencesParam
import team.four.mys.domain.usecases.LocaleHelperUseCase
import team.four.mys.presentation.activity.MainActivity
import team.four.mys.presentation.adapters.LanguageAdapter
import team.four.mys.presentation.viewmodelsactivity.LanguageViewModel

class LanguageFragment : Fragment() {

    private var _binding: FragmentLanguageBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModel<LanguageViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLanguageBinding.inflate(inflater, container, false)

        binding.buttonArrowLeft.setOnClickListener {
            (activity as MainActivity).replaceFragment(SettingsFragment())
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
                val intent = Intent(requireContext(), MainActivity::class.java)
                intent.putExtra("fragment", "SettingsFragment")
                startActivity(intent)
            }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapterLanguage
    }

    companion object {
        val language = listOf(
            Language(R.drawable.language_usa, "USA"),
            Language(R.drawable.language_russia, "Russia")
        )
    }

}